package com.excilys.computerdatabase.ui;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.excilys.computerdatabase.cliconfig.JacksonConfig;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.paginator.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class CLI {
	private static final String HOST = "http://localhost:8080/cdb/";
	public static final String GENERAL_API_ENDPOINT = "api/v1.0.0";
    public static final String COMPUTERS_ENDPOINT = "computers/";
    public static final String COMPANIES_ENDPOINT = "companies/";
    private static WebTarget computersEndpoint;
    private static WebTarget companiesEndpoint;
    
	private static Boolean running = true;
	private static Scanner reader = new Scanner(System.in);

	@Autowired
    public CLI (@Qualifier("java8jacksonmapper") ObjectMapper objectMapper) {
        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        jacksonProvider.setMapper(objectMapper);
        Client client = ClientBuilder.newClient(new ClientConfig(jacksonProvider));
        
        WebTarget generalEndpoint = client.target(HOST + GENERAL_API_ENDPOINT);
        computersEndpoint = generalEndpoint.path(COMPUTERS_ENDPOINT);
        companiesEndpoint = generalEndpoint.path(COMPANIES_ENDPOINT);
	}
	
	private static void printOptions() {
		System.out.println("What do you want to do?");
		System.out.println("#1 Show the list of companies");
		System.out.println("#2 Show the list of computers");
		System.out.println("#3 Show details of one computer");
		System.out.println("#4 Create a computer");
		System.out.println("#5 Update a computer");
		System.out.println("#6 Delete a computer");
		System.out.println("#7 Exit");
	}

	private static void choice() {
		if(reader.hasNextInt()) {
			switch(reader.nextInt()) {
			case 1 :
				printCompanies();
				break;
			case 2 : 
				printComputers();
				break;
			case 3 : 
				printDetailsComputer();
				break;
			case 4:
				createComputer();
				break;
			case 5 :
				updateComputer();
				break;
			case 6 :
				deleteComputer();
				break;
			case 7 :
				running = false;
				System.out.println("You exited the database.");
				break;
			default:
				System.out.println("Incorrect choice");
				break;
			}
		} else {
			reader.next();
			System.out.println("Incorrect choice");
		}
	}

	private static void printCompanies() {
		ArrayList<Company> companies = (ArrayList<Company>) Arrays.stream(companiesEndpoint.request(MediaType.APPLICATION_JSON).get(Company[].class)).collect(Collectors.toList());
		
		for (Company company : companies) {
			System.out.println(company.getId() + " : " + company.getName());
		}
	}

	private static void printComputers() {
		Page<Computer> page = new Page<Computer>();
		ArrayList<Computer> computers = (ArrayList<Computer>) Arrays.stream(computersEndpoint.request(MediaType.APPLICATION_JSON).get(Computer[].class)).collect(Collectors.toList());
		
		reader.nextLine();
				
		while(page.hasNextPage(computers)) {
			if(page.hasNextPage(computers)) {
				for (Computer computer : page.get(computers)) {
					System.out.println(computer.getId() + " : " + computer.getName());
				}
				System.out.println("What do you want to do? (previous : p, next : n, quit : q)");
				if(reader.hasNextLine()) {
					String line = reader.nextLine();
					if(line.compareToIgnoreCase("p") == 0) {
						page.previousPage();
						if(page.hasPreviousPage()){
							page.previousPage();
						}
						else {
							System.out.println("There are no previous pages.");
						}
					} else if (line.compareToIgnoreCase("n") == 0) {
						page.nextPage();
					} else {
						break;
					}
				}
			}
		}
	}

	private static void printDetailsComputer() {
		System.out.println("Select the index of the computer");

		if(reader.hasNextInt()) {
			Integer choice = reader.nextInt();
			Computer comp = computersEndpoint.path("detail/"+String.valueOf(choice)).request(MediaType.APPLICATION_JSON).get(Computer.class);
			System.out.println("Name : "+comp.getName() + ", Introduced date : "+comp.getIntroducedDate()+", Discounted date : "+comp.getDiscontinuedDate()+
					", Company : "+(comp.getCompany() == null ? null : comp.getCompany().getName()));
		}
	}

	private static void createComputer() {
		String name = null;
		LocalDate introducedDate = null;
		LocalDate discountedDate = null;
		String companyName = null;

		System.out.println("What is the name of the computer?");

		reader.nextLine();

		if(reader.hasNextLine()) {
			name = reader.nextLine();
		}

		System.out.println("What is the date that the computer was introduced? (Format YYYY-MM-DD)");

		if(reader.hasNextLine()) {
			String line = reader.nextLine();
			if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
				introducedDate = Date.valueOf(line).toLocalDate(); 
			}
			else
				System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
		}

		System.out.println("What is the date that the computer was discounted? (Format YYYY-MM-DD)");

		if(reader.hasNextLine()) {
			String line = reader.nextLine();
			if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
				discountedDate = Date.valueOf(line).toLocalDate();
				if(introducedDate != null && discountedDate.isBefore(introducedDate))
					discountedDate = null;
			}
			else
				System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
		}

		System.out.println("What is the name of the manufacturing company?");

		if(reader.hasNextLine())
			companyName = reader.nextLine();

		Company company = companiesEndpoint.path("detail/"+companyName).request(MediaType.APPLICATION_JSON).get(Company.class);
		Computer comp = new Computer(0, name, introducedDate, discountedDate, company);
		computersEndpoint.request(MediaType.APPLICATION_JSON).post(Entity.entity(comp, MediaType.APPLICATION_JSON));
	}

	private static void updateComputer() {
		System.out.println("Select the computer you want to update.");

		reader.nextLine();

		if(reader.hasNextInt()) {
			Integer choice = reader.nextInt();

			Computer comp = computersEndpoint.path("detail/"+String.valueOf(choice)).request(MediaType.APPLICATION_JSON).get(Computer.class);
			long id = comp.getId();
			String name = comp.getName();
			LocalDate introducedDate = comp.getIntroducedDate();
			LocalDate discountedDate = comp.getDiscontinuedDate();
			Company companyName = comp.getCompany();

			System.out.println("What is the name of the computer? Current : " + name);

			if(reader.hasNextLine())
				name = reader.nextLine();

			System.out.println("What is the date that the computer was introduced? (Format YYYY-MM-DD) Current : " + introducedDate);

			if(reader.hasNextLine()) {
				String line = reader.nextLine();
				if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
					introducedDate = Date.valueOf(line).toLocalDate();
				}
				else
					System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
			}

			System.out.println("What is the date that the computer was discounted? (Format YYYY-MM-DD) Current : " + discountedDate);

			if(reader.hasNextLine()) {
				String line = reader.nextLine();
				if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
					discountedDate = Date.valueOf(line).toLocalDate();
					if(introducedDate != null && discountedDate.isBefore(introducedDate))
						discountedDate = null;
				}
				else
					System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
			}

			System.out.println("What is the name of the manufacturing company? Current : " + companyName.getName());

			if(reader.hasNextLine()){
				companyName = companiesEndpoint.path("detail/"+reader.nextLine()).request(MediaType.APPLICATION_JSON).get(Company.class);
			}

			Computer computer = new Computer(id, name, introducedDate, discountedDate, companyName);
			computersEndpoint.path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).put(Entity.entity(computer, MediaType.APPLICATION_JSON));
		}
	}

	private static void deleteComputer() {
		System.out.println("Select the computer you want to delete.");

		if(reader.hasNextInt()) {
			Integer choice = reader.nextInt();

			computersEndpoint.path(String.valueOf(choice)).request(MediaType.APPLICATION_JSON).delete();
		}
	}

	public static void main(String[] args) {
		new CLI(JacksonConfig.jacksonObjectMapper());
		
		while(running){
			printOptions();
			choice();
			System.out.println("\n");
		}
		reader.close();
	}
}


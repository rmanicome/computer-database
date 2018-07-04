package com.excilys.computerdatabase.ui;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Page;
import com.excilys.computerdatabase.persistence.SQLCompanyQuery;
import com.excilys.computerdatabase.service.CompanyValues;
import com.excilys.computerdatabase.service.ComputerValues;

public class CLI {
	private static ComputerValues computerValues = new ComputerValues();
	private static CompanyValues companyValues = new CompanyValues();
	private static Boolean running = true;
	private static Scanner reader = new Scanner(System.in);
	
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
		for (Company company : companyValues.getCompanyList()) {
			System.out.println(companyValues.getCompanyList().indexOf(company) + " : " + company.getName());
		}
	}
	
	private static void printComputers() {
		Page<Computer> page = new Page<Computer>();
		
		reader.nextLine();
		
		while(page.hasNextPage(computerValues)) {
			if(page.hasNextPage(computerValues)) {
				for (Computer computer : page.getPage(computerValues.getComputerList())) {
					System.out.println((computerValues.getComputerList().indexOf(computer)+1) + " : " + computer.getName());
				}
				System.out.println("What do you want to do? (previous : p, next : n, quit : q)");
				if(reader.hasNextLine()) {
					String line = reader.nextLine();
					if(line.compareToIgnoreCase("p") == 0) {
						page.getPreviousPage();
						if(page.hasPreviousPage()){
							page.getPreviousPage();
						}
						else {
							System.out.println("There are no previous pages.");
						}
					} else if (line.compareToIgnoreCase("n") == 0) {
						page.getNextPage();
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
			Integer choice = reader.nextInt()-1;
			
			if(choice >= computerValues.getComputerList().size()) {
				System.out.println("Choice not in the database");
				return;
			}
			Computer comp = computerValues.getComputerList().get(choice);
			System.out.println("Name : "+comp.getName() + ", Introduced date : "+comp.getIntroducedDate()+", Discounted date : "+comp.getDiscountedDate()+
					", Company : "+(comp.getCompany().getId() == 0||comp.getCompany() == null ? null : companyValues.getCompanyList().get(comp.getCompany().getId()-1).getName()));
		}
	}
	
	private static void createComputer() {
		String name = null;
		Date introducedDate = null;
		Date discountedDate = null;
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
				introducedDate = Date.valueOf(line); 
			}
			else
				System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
		}
		
		System.out.println("What is the date that the computer was discounted? (Format YYYY-MM-DD)");
		
		if(reader.hasNextLine()) {
			String line = reader.nextLine();
			if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
				discountedDate = Date.valueOf(line);
				if(introducedDate != null && discountedDate.before(introducedDate))
					discountedDate = null;
			}
			else
				System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
		}
		
		System.out.println("What is the name of the manufacturing company?");
		
		if(reader.hasNextLine())
			companyName = reader.nextLine();
		
		try {
			Computer comp = new Computer(Computer.getBiggestId()+1, name, introducedDate, discountedDate, SQLCompanyQuery.getCompany(companyName));
			computerValues.addComputer(comp);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void updateComputer() {
		System.out.println("Select the computer you want to update (index of the computer in the list).");
		
		reader.nextLine();
		
		if(reader.hasNextInt()) {
			Integer choice = reader.nextInt()-1;
			
			if(choice >= computerValues.getComputerList().size()) {
				System.out.println("Choice not in the database");
				return;
			}
			
			Computer comp = computerValues.getComputerList().get(choice);
			long id = comp.getId();
			String name = comp.getName();
			Date introducedDate = comp.getIntroducedDate();
			Date discountedDate = comp.getDiscountedDate();
			Company companyName = comp.getCompany();
			
			System.out.println("What is the name of the computer? Current : " + name);
			
			if(reader.hasNextLine())
				name = reader.nextLine();
			
			System.out.println("What is the date that the computer was introduced? (Format YYYY-MM-DD) Current : " + introducedDate);
			
			if(reader.hasNextLine()) {
				String line = reader.nextLine();
				if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
					introducedDate = Date.valueOf(line);
				}
				else
					System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
			}
			
			System.out.println("What is the date that the computer was discounted? (Format YYYY-MM-DD) Current : " + discountedDate);
			
			if(reader.hasNextLine()) {
				String line = reader.nextLine();
				if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
					discountedDate = Date.valueOf(line);
					if(introducedDate != null && discountedDate.before(introducedDate))
						discountedDate = null;
				}
				else
					System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
			}
			
			System.out.println("What is the name of the manufacturing company? Current : " + companyName.getName());
			
			if(reader.hasNextLine()){
				try {
					companyName = SQLCompanyQuery.getCompany(reader.nextLine());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
			try {
				Computer computer = new Computer(id, name, introducedDate, discountedDate, companyName);
				computerValues.updateComputer(choice, computer);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void deleteComputer() {
		System.out.println("Select the computer you want to delete (index of the computer in the list).");
		
		if(reader.hasNextInt()) {
			Integer choice = reader.nextInt()-1;
			if(choice >= computerValues.getComputerList().size()) {
				System.out.println("Choice not in the database");
				return;
			}
			
			try {
				computerValues.deleteComputer(computerValues.getComputerList().get(choice));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		while(running){
			printOptions();
			choice();
			System.out.println("\n");
		}
		reader.close();
	}
}

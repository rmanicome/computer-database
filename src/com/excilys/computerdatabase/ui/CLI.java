package com.excilys.computerdatabase.ui;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.tomcat.util.file.Matcher;

import com.excilys.computerdatabase.mapper.Values;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.SQLQuery;

import jdk.nashorn.internal.parser.DateParser;

public class CLI {
	private static Values values;
	
	public CLI() throws SQLException{
		values = new Values();
	}
	
	private static void printOptions(){
		System.out.println("What do you want to do?");
		System.out.println("#1 Show the list of companies");
		System.out.println("#2 Show the list of computers");
		System.out.println("#3 Show details of one computer");
		System.out.println("#4 Create a computer");
		System.out.println("#5 Update a computer");
		System.out.println("#6 Delete a computer");
	}
	
	private static void choice() throws SQLException{
		Scanner reader = new Scanner(System.in);
		
		if(reader.hasNextInt()){
			switch(reader.nextInt()){
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
			default:
				System.out.println("Le choix n'est pas correct");
				break;
			}
		}
		
		reader.close();
	}
	
	private static void printCompanies(){
		for (Company company : values.getCompanyList()) {
			System.out.println(values.getCompanyList().indexOf(company) + " : " + company.getName());
		}
	}
	
	private static void printComputers(){
		for (Computer computer : values.getComputerList()) {
			System.out.println((values.getComputerList().indexOf(computer)+1) + " : " + computer.getName());
		}
	}
	
	private static void printDetailsComputer(){
		System.out.println("Select the index of the computer");
		Scanner reader = new Scanner(System.in);
		
		if(reader.hasNextInt()){
			Computer comp = values.getComputerList().get(reader.nextInt()-1);
			System.out.println("Name : "+comp.getName() + ", Introduced date : "+comp.getIntroducedDate()+", Discounted date : "+comp.getDiscountedDate()+", Company : "+values.getCompanyList().get(comp.getCompany()).getName());
		}
		
		reader.close();
	}
	
	private static void createComputer() throws SQLException{
		String name = null;
		Date introducedDate = null;
		Date discountedDate = null;
		String companyName = null;
		
		System.out.println("What is the name of the computer?");
		Scanner reader = new Scanner(System.in);
		
		if(reader.hasNext())
			name = reader.nextLine();
		
		System.out.println("What is the date that the computer was introduced? (Format YYYY-MM-DD)");
		
		if(reader.hasNext()){
			String line = reader.nextLine();
			if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")){
				introducedDate = Date.valueOf(line);
			}
			else
				System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
		}
		
		System.out.println("What is the date that the computer was discounted? (Format YYYY-MM-DD)");
		
		if(reader.hasNext()){
			String line = reader.nextLine();
			if(line.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")){
				discountedDate = Date.valueOf(line);
			}
			else
				System.out.println("The date format is incorrect. No date have been saved, you can change the date later.");
		}
		
		System.out.println("What is the name of the manufacturing company?");
		
		if(reader.hasNext())
			companyName = reader.nextLine();
		
		Computer comp = new Computer(Computer.getBiggestId()+1,name, introducedDate, discountedDate, SQLQuery.getCompanyId(companyName));
		values.addComputer(comp);
		
		reader.close();
	}
	
	private static void updateComputer() throws SQLException{
		System.out.println("Select the computer you want to update (index of the computer in the list).");
		Scanner reader = new Scanner(System.in);
		
		if(reader.hasNextInt()){
			
		}
		
		reader.close();
	}
	
	private static void deleteComputer() throws SQLException{
		System.out.println("Select the computer you want to delete (index of the computer in the list).");
		Scanner reader = new Scanner(System.in);
		
		if(reader.hasNextInt())
			values.deleteComputer(values.getComputerList().get(reader.nextInt()));
		
		reader.close();
	}
	
	public static void main(String[] args) {
		try {
			CLI cli = new CLI();
			printOptions();
			choice();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}

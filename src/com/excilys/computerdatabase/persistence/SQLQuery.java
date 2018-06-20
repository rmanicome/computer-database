package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class SQLQuery {
	private static Connection con = Connexion.getInstance().getConnection();
	
	public static ArrayList<Company> getCompanies() throws SQLException{
		ArrayList<Company> companyList = new ArrayList<Company>();
		String companyQuery = "SELECT * FROM company;";
		ResultSet companies;
		
		Statement stmt = con.createStatement();
		companies = stmt.executeQuery(companyQuery);
		
		while(companies.next()){
			companyList.add(new Company(companies.getString(2)));
		}
		
		return companyList;
	}
	
	public static ArrayList<Computer> getComputers() throws SQLException{
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		String computerQuery = "SELECT * FROM computer;";
		ResultSet computers;
		
		Statement stmt = con.createStatement();
		computers = stmt.executeQuery(computerQuery);
		
		while(computers.next()){
			computerList.add(new Computer(computers.getInt(1), computers.getString(2), computers.getDate(3), computers.getDate(4), computers.getInt(5)));
		}
		
		return computerList;
	}
	
	public static Integer getCompanyId(String name) throws SQLException{
		String companyQuery = "SELECT id FROM company WHERE name = '"+name+"';";
		ResultSet id;
		Statement stmt = con.createStatement();
		id = stmt.executeQuery(companyQuery);
		
		if(id.next())
			return id.getInt(1);
		
		return null;
	}
	
	public static void addComputer(Computer comp) throws SQLException{
		String computerQuery = "INSERT INTO computer (name,introduced,discontinued,company_id) values ('"+comp.getName()+"',"+
				(comp.getIntroducedDate() == null ? comp.getIntroducedDate() : "'"+comp.getIntroducedDate()+"'")+","+
				(comp.getDiscountedDate() == null ? comp.getDiscountedDate() : "'"+comp.getDiscountedDate()+"'")+","+
				comp.getCompany()+");";
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate(computerQuery);
	}
	
	public static void updateComputer(Computer comp) throws SQLException{
		String computerQuery = "UPDATE computer SET introduced = "+
				(comp.getIntroducedDate() == null ? comp.getIntroducedDate() : "'"+comp.getIntroducedDate()+"'")+", discontinued = "+
				(comp.getDiscountedDate() == null ? comp.getDiscountedDate() : "'"+comp.getDiscountedDate()+"'")+", company_id = "+
				comp.getCompany()+" WHERE id = '"+comp.getId()+"';";
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate(computerQuery);
	}
	
	public static void deleteComputer(Computer comp) throws SQLException{
		String computerQuery = "DELETE FROM computer WHERE id = '"+comp.getId()+"';";
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate(computerQuery);
	}
	
}

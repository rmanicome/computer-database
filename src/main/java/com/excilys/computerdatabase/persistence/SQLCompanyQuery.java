package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Constant;

public class SQLCompanyQuery {
	private static Connection connexion = Connexion.getInstance().getConnection();
	
	public static ArrayList<Company> getCompanies() throws SQLException {
		ArrayList<Company> companyList = new ArrayList<Company>();
		String companyQuery = "SELECT * FROM "+Constant.COMPANY_TABLE+";";
		ResultSet companies;

		Statement stmt = connexion.createStatement();
		companies = stmt.executeQuery(companyQuery);

		while(companies.next()){
			companyList.add(new Company(companies.getInt(1),companies.getString(2)));
		}

		return companyList;
	}
	
	public static Company getCompany(String name) throws SQLException {
		String companyQuery = "SELECT * FROM "+Constant.COMPANY_TABLE+" WHERE "+Constant.COMPANY_NAME+" = '"+name+"';";
		ResultSet id;
		Statement stmt = connexion.createStatement();
		id = stmt.executeQuery(companyQuery);

		if(id.next())
			return new Company(id.getInt(1),id.getString(2));

		return null;
	}
	
	public static Company getCompany(Integer id) throws SQLException {
		String companyQuery = "SELECT * FROM "+Constant.COMPANY_TABLE+" WHERE "+Constant.COMPANY_ID+" = '"+id+"';";
		ResultSet company;
		Statement stmt = connexion.createStatement();
		company = stmt.executeQuery(companyQuery);

		if(company.next())
			return new Company(company.getInt(1),company.getString(2));

		return null;
	}

}

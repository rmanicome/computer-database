package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAO {
	final static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	private final static CompanyDAO INSTANCE = new CompanyDAO();
	private final static String GET = "SELECT "+ConstantBD.COMPANY_ID+","+ConstantBD.COMPANY_NAME+" FROM "+ConstantBD.COMPANY_TABLE+";";
	private final static String GET_BY_NAME = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_NAME+" = '1';"; 
	private final static String GET_BY_ID = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = '1';";
	
	private CompanyDAO(){
		
	}
	
	public static CompanyDAO getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<Company> get() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		try {
			Connection connexion = Connexion.getInstance().getConnection();
			ResultSet companies;
			Statement stmt = connexion.createStatement();

			companies = stmt.executeQuery(GET);

			while(companies.next()){
				companyList.add(new Company(companies.getInt(1),companies.getString(2)));
			}
			return companyList;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return companyList;
		}
	}
	
	public Optional<Company> get(String name) {
		try {
			Connection connexion = Connexion.getInstance().getConnection();
			ResultSet id;
			Statement stmt = connexion.createStatement();
			
			String query = GET_BY_NAME.toString();
			query = query.replace("1", name);
			
			id = stmt.executeQuery(query);

			if(id.next())
				return Optional.of(new Company(id.getInt(1),id.getString(2)));
			
			return Optional.empty();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return Optional.empty();
		}
}
	
	public Optional<Company> get(Integer id) {
		try {
			Connection connexion = Connexion.getInstance().getConnection();
			ResultSet company;
			Statement stmt = connexion.createStatement();
			
			String query = GET_BY_ID.toString();
			query = query.replace("1", id.toString());
			
			company = stmt.executeQuery(query);
			
			if(company.next())
				return Optional.of(new Company(company.getInt(1),company.getString(2)));
			
			return Optional.empty();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return Optional.empty();
		}
	}

}

package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private final static String GET_BY_NAME = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_NAME+" = ?;"; 
	private final static String GET_BY_ID = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = ?;";
	private final static String DELETE = "DELETE FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = ?;";
	private final static String DELETE_COMPUTER = "DELETE FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_COMPANY_ID+" = ?;";
	
	private CompanyDAO(){
		
	}
	
	public static CompanyDAO getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<Company> get() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		try (Connection connexion = ConnectionPool.getConnection()){
			ResultSet companies;
			Statement stmt = connexion.createStatement();

			companies = stmt.executeQuery(GET);

			while(companies.next()){
				companyList.add(new Company(companies.getInt(1),companies.getString(2)));
			}
			
			stmt.close();
			
			return companyList;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return companyList;
		}
	}
	
	public Optional<Company> get(String name) {
		try (Connection connexion = ConnectionPool.getConnection()){
			ResultSet id;
			
			PreparedStatement query = connexion.prepareStatement(GET_BY_NAME);
			query.setString(1, name);
			id = query.executeQuery();

			if(id.next()){
				Company comp = new Company(id.getInt(1),id.getString(2));
				return Optional.of(comp);
			}
			
			return Optional.empty();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return Optional.empty();
		}
}
	
	public Optional<Company> get(Integer id) {
		try (Connection connexion = ConnectionPool.getConnection()){
			ResultSet company;
			
			PreparedStatement query = connexion.prepareStatement(GET_BY_ID);
			query.setInt(1, id);
			company = query.executeQuery();
			
			if(company.next()){
				Company comp = new Company(company.getInt(1),company.getString(2));
				return Optional.of(comp);
			}
			
			return Optional.empty();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return Optional.empty();
		}
	}
	
	public void delete(Integer id) {
		try (Connection connexion = ConnectionPool.getConnection()){
			connexion.setAutoCommit(false);
			
			PreparedStatement queryCompany = connexion.prepareStatement(DELETE);
			PreparedStatement queryComputer = connexion.prepareStatement(DELETE_COMPUTER);
			queryCompany.setInt(1, id);
			queryComputer.setInt(1, id);
			queryCompany.executeUpdate();
			queryComputer.executeUpdate();
			
			connexion.commit();
			
			connexion.setAutoCommit(true);
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
}

package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAONew {
	private final static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private JdbcTemplate jdbcTemplate;
	
	private final static String GET = "SELECT "+ConstantBD.COMPANY_ID+","+ConstantBD.COMPANY_NAME+" FROM "+ConstantBD.COMPANY_TABLE+";";
	private final static String GET_BY_NAME = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_NAME+" = ?;"; 
	private final static String GET_BY_ID = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = ?;";
	private final static String DELETE = "DELETE FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = ?;";
	private final static String DELETE_COMPUTER = "DELETE FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_COMPANY_ID+" = ?;";
	
	public ArrayList<Company> get() {
		ArrayList<Company> companyList = new ArrayList<Company>();

			jdbcTemplate.execute(GET);
			jdbcTemplate.
			while(companies.next()){
				companyList.add(new Company(companies.getInt(1),companies.getString(2)));
			}
			
			stmt.close();
			
			return companyList;
	}
}

package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.SQLCompanyQuery;

public class CompanyValues {
	private ArrayList<Company> companyList;
	
	public CompanyValues() {
		try {
			companyList = SQLCompanyQuery.getCompanies();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Company> getCompanyList() {
		return companyList;
	}

}

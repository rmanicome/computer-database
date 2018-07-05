package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;

public class CompanyService {
	private final static CompanyService INSTANCE = new CompanyService();
	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	private CompanyService() {

	}
	
	public static CompanyService getInstance() {
		return INSTANCE;
	}
		
	public ArrayList<Company> get() {
		return companyDAO.get();
	}
	
	public Optional<Company> get(String name) {
		return companyDAO.get(name);
	}
	
	public Optional<Company> get(Integer id) {
		return companyDAO.get(id);
	}

}

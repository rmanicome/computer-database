package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;

@Service
public class CompanyService {
	private final static CompanyService INSTANCE = new CompanyService();
	@Resource(name="companyDAO")
	private CompanyDAO companyDAO;
	
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
	
	public void delete(Company company){
		companyDAO.delete(company.getId());
	}
}

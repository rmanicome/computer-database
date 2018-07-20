package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	
	public ArrayList<Company> get() {
		return companyDAO.get();
	}
	
	public Optional<Company> get(String name) {
		return companyDAO.get(name);
	}
	
	public Optional<Company> get(Long id) {
		return companyDAO.get(id);
	}
	
	/*public void delete(Company company){
		companyDAO.delete(company.getId());
	}*/
}

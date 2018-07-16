package com.excilys.computerdatabase.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.CompanyValidator;
import com.excilys.computerdatabase.validator.ComputerValidator;

@Configuration
public class Application {
	@Bean
	public ComputerDAO computerDao() {
		return ComputerDAO.getInstance();
	}
	
	@Bean
	public CompanyDAO companyDao() {
		return CompanyDAO.getInstance();
	}
	
	@Bean
	public CompanyService companyService() {
		return CompanyService.getInstance();
	}
	
	@Bean
	public ComputerService computerService(){
		return ComputerService.getInstance();
	}
	
	@Bean
	public ComputerValidator computerValidator(){
		return ComputerValidator.getInstance();
	}

	@Bean
	public CompanyValidator companyValidator(){
		return CompanyValidator.getInstance();
	}
	
	@Bean
	public ComputerMapper computerMapper(){
		return ComputerMapper.getInstance();
	}
}

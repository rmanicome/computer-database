package com.excilys.computerdatabase.mapper;

import java.sql.Date;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;

public class ComputerMapper {
	private final static ComputerMapper INSTANCE = new ComputerMapper();
	
	private ComputerMapper(){
		
	}
	
	public static ComputerMapper getInstance() {
		return INSTANCE;
	}
	
	private Date getDate(String date) {
		return date == "" ? null : Date.valueOf(date);
	}
	
	private Company getCompany(String idCompany) {
		return CompanyService.getInstance().get(Integer.parseInt(idCompany)).orElse(null);
	}
	
	public Computer nom(String id, String name, String introduced, String discontinued, String idCompany) {
		return new Computer.ComputerBuilder(name).withId(Long.parseLong(id)).withIntroducedDate(getDate(introduced))
				.withDiscountedDate(getDate(discontinued))
				.withCompany(getCompany(idCompany)).build();
	}
}

package com.excilys.computerdatabase.mapper;

import java.sql.Date;

import javax.annotation.Resource;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;

@Resource
public class ComputerMapper {
	private final static ComputerMapper INSTANCE = new ComputerMapper();
	
	private ComputerMapper(){
		
	}
	
	public static ComputerMapper getInstance() {
		return INSTANCE;
	}
	
	public Computer nom(String id, String name, String introduced, String discontinued, String idCompany) {
		return new Computer.ComputerBuilder(name).withId(Long.parseLong(id)).withIntroducedDate(introduced == "" ? null : Date.valueOf(introduced))
				.withDiscountedDate(discontinued == "" ? null : Date.valueOf(discontinued))
				.withCompany(CompanyService.getInstance().get(Integer.parseInt(idCompany)).orElse(null)).build();
	}
}

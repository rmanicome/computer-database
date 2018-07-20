package com.excilys.computerdatabase.mapper;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;

@Component
public class ComputerMapper {
	@Autowired
	private CompanyService companyService;
	
	public Computer nom(String id, String name, String introduced, String discontinued, String idCompany) {
		return new Computer.ComputerBuilder(name).withId(Long.parseLong(id)).withIntroducedDate(introduced == "" ? null : Date.valueOf(introduced))
				.withDiscountedDate(discontinued == "" ? null : Date.valueOf(discontinued))
				.withCompany(companyService.get(Integer.parseInt(idCompany)).orElse(null)).build();
	}
}

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
	
	public Computer buildComputer(String id, String name, String introduced, String discontinued, String idCompany) {
		Long companyId = Long.parseLong(idCompany);
		
		return new Computer.ComputerBuilder(name).withId(Long.parseLong(id)).withIntroducedDate(introduced == "" ? null : Date.valueOf(introduced).toLocalDate())
				.withDiscountedDate(discontinued == "" ? null : Date.valueOf(discontinued).toLocalDate())
				.withCompany(companyId == 0 ? null : companyService.get(companyId).get()).build();
	}
}

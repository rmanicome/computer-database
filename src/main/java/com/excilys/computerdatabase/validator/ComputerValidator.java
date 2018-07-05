package com.excilys.computerdatabase.validator;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.excilys.computerdatabase.service.CompanyService;

public class ComputerValidator {
	private final static ComputerValidator INSTANCE = new ComputerValidator();
		
	private ComputerValidator() {
		
	}
	
	public static ComputerValidator getInstance() {
		return INSTANCE;
	}
	
	public Boolean checkName(String name){
		return name.length() > 1 && !name.contains(",") && !name.contains(";") && !name.contains("'");
	}
	
	public Boolean checkDateFormat(String date){
		return date.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]");
	}
	
	public Boolean checkIntroduced(String introduced){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date today = new java.util.Date();
		return checkDateFormat(introduced) && Date.valueOf(introduced).before(Date.valueOf(dateFormat.format(today)));
	}
	
	public Boolean checkDiscontinued(String introduced, String discontinued){
		return checkDateFormat(discontinued) && Date.valueOf(discontinued).before(Date.valueOf(introduced)); 
	}
	
	public Boolean checkCompanyId(String id){
		return id.matches("[0-9]*") && CompanyService.getInstance().get(Integer.getInteger(id)).isPresent();
	}
	
	public Boolean checkComputer(String name, String introduced, String discontinued, String id){
		return checkName(name) && checkIntroduced(introduced) && checkDiscontinued(introduced, discontinued) && checkCompanyId(id);
	}
}

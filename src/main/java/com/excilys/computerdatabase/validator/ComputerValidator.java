package com.excilys.computerdatabase.validator;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.excilys.computerdatabase.service.CompanyService;

public class ComputerValidator {
	private final static ComputerValidator INSTANCE = new ComputerValidator();
	private final static String SEPARATOR = "-";
	
	private ComputerValidator() {
		
	}
	
	public static ComputerValidator getInstance() {
		return INSTANCE;
	}
	
	private Boolean checkName(String name){
		return name.length() > 1 && !name.contains(",") && !name.contains(";") && !name.contains("'");
	}
	
	private Boolean checkDateFormat(String date){
		return date.substring(0, date.indexOf(SEPARATOR)).matches("[1-2][0-9]{3}") && 
				date.substring(date.indexOf(SEPARATOR) + 1, date.lastIndexOf(SEPARATOR)).matches("0?[1-9]|1[0-2]") && 
				date.substring(date.lastIndexOf(SEPARATOR) + 1).matches("[0-2][0-9]|3[0-1]");
	}
	
	private Boolean checkDateReality(String date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date today = new java.util.Date();
		return Date.valueOf(date).before(Date.valueOf(dateFormat.format(today))) || Date.valueOf(date).equals(Date.valueOf(dateFormat.format(today)));
	}
	
	private Boolean checkIntroduced(String introduced){
		
		return introduced == "" || (checkDateFormat(introduced) && checkDateReality(introduced));
	}
	
	private Boolean checkDiscontinued(String introduced, String discontinued){
		return discontinued == "" || (checkDateFormat(discontinued) && checkDateReality(discontinued) && Date.valueOf(discontinued).after(Date.valueOf(introduced))); 
	}
	
	private Boolean checkCompanyId(String id){
		return id == null || Integer.parseInt(id) == 0 || (id.matches("[1-9][0-9]*") && CompanyService.getInstance().get(Integer.parseInt(id)).isPresent());
	}
	
	public Boolean checkComputer(String name, String introduced, String discontinued, String id){
		return checkName(name) && checkIntroduced(introduced) && checkDiscontinued(introduced, discontinued) && checkCompanyId(id);
	}
}

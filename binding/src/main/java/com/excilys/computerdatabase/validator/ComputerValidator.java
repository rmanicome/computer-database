package com.excilys.computerdatabase.validator;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.service.CompanyService;

@Component
public class ComputerValidator {
	private final static String SEPARATOR = "-";
	@Autowired
	private CompanyService companyService;
	
	private void checkName(String name) throws IncorrectInputException {
		if(name.length() < 1) 
			throw new IncorrectInputException("The name can't be empty");
		else if (name.contains(",") || name.contains(";") || name.contains("'"))
			throw new IncorrectInputException("The name can't contain caracters like ',',';','''");
	}
	
	private void checkDateFormat(String date) throws IncorrectInputException {
		if(!date.substring(0, date.indexOf(SEPARATOR)).matches("[1-2][0-9]{3}") || 
			!date.substring(date.indexOf(SEPARATOR) + 1, date.lastIndexOf(SEPARATOR)).matches("0?[1-9]|1[0-2]") || 
			!date.substring(date.lastIndexOf(SEPARATOR) + 1).matches("[0-2][0-9]|3[0-1]"))
			throw new IncorrectInputException("The date format is not correct");
	}
	
	private void checkDateReality(String date) throws IncorrectInputException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date today = new java.util.Date();
		
		if(Date.valueOf(date).after(Date.valueOf(dateFormat.format(today))) || Date.valueOf(date).equals(Date.valueOf(dateFormat.format(today))))
			throw new IncorrectInputException("The date can't be after or the dame day as today's date");
	}
	
	private void checkIntroduced(String introduced) throws IncorrectInputException{
		if(introduced != ""){
			checkDateFormat(introduced);
			checkDateReality(introduced);
		}
	}
	
	private void checkDiscontinued(String introduced, String discontinued) throws IncorrectInputException {
		if(discontinued != ""){
			checkDateFormat(discontinued);
			checkDateReality(discontinued);
			if(Date.valueOf(discontinued).before(Date.valueOf(introduced)))
				throw new IncorrectInputException("The discontinued date can't be before the introduced date");
		}
	}
	
	private void checkCompanyId(String id) throws IncorrectInputException {
		if(id != null && Integer.parseInt(id) != 0){
			if(!id.matches("[1-9][0-9]*") || !companyService.get(Long.parseLong(id)).isPresent())
				throw new IncorrectInputException("The application encountered an error with the company service");
		}
	}
	
	public void checkComputer(String name, String introduced, String discontinued, String id) throws IncorrectInputException {
		checkName(name); 
		checkIntroduced(introduced); 
		checkDiscontinued(introduced, discontinued); 
		checkCompanyId(id);
	}
}

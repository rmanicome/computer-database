package com.excilys.computerdatabase.validator;

import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {
	public void checkCompany(String name) throws IncorrectInputException {
		if(name==null || name.length() < 1) 
			throw new IncorrectInputException("The name can't be empty");
		else if (name.contains(",") || name.contains(";") || name.contains("'"))
			throw new IncorrectInputException("The name can't contain caracters like ',',';','''");
	}
}

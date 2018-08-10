package com.excilys.computerdatabase.validator;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {
	private void checkName(String name) throws IncorrectInputException {
		if(name.length() < 1) 
			throw new IncorrectInputException("The name can't be empty");
		else if (name.contains(",") || name.contains(";") || name.contains("'"))
			throw new IncorrectInputException("The name can't contain caracters like ',',';','''");
	}
	
	private void checkPassword(String password) throws IncorrectInputException {
		if(password.length() < 1) 
			throw new IncorrectInputException("The name can't be empty");
		else if (password.contains(",") || password.contains(";") || password.contains("'"))
			throw new IncorrectInputException("The name can't contain caracters like ',',';','''");
	}
	
	public void checkUserInput(String name, String password) throws IncorrectInputException {
		checkName(name);
		checkPassword(password);
	}
}

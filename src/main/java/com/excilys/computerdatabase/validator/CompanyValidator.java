package com.excilys.computerdatabase.validator;

public class CompanyValidator {
	private final static CompanyValidator INSTANCE = new CompanyValidator();

	private CompanyValidator() {
		
	}
	
	public static CompanyValidator getInstance() {
		return INSTANCE;
	}
	
	public Boolean checkCompany(String name) {
		return name.length() > 1 && !name.contains(",") && !name.contains("'") && !name.contains(";");
	}
}

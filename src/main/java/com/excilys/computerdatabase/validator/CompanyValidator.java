package com.excilys.computerdatabase.validator;

import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {
	public Boolean checkCompany(String name) {
		return name.length() > 1 && !name.contains(",") && !name.contains("'") && !name.contains(";");
	}
}

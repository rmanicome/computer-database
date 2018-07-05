package com.excilys.computerdatabase.service;

import junit.framework.TestCase;

public class CompanyValuesTest extends TestCase {
	
	public void testGetCompanyList() {
		CompanyService comp = new CompanyService();
		assertNotNull(comp.getCompanyList());
	}
}

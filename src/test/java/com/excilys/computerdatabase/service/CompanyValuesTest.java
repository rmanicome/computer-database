package com.excilys.computerdatabase.service;

import junit.framework.TestCase;

public class CompanyValuesTest extends TestCase {
	
	public void testGetCompanyList() {
		CompanyValues comp = new CompanyValues();
		assertNotNull(comp.getCompanyList());
	}
}

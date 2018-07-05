package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;
import junit.framework.TestCase;

public class SQLCompanyQueryTest extends TestCase {
	
	public void testGetCompanies() {
		try {
			assertNotNull(CompanyDAO.getCompanies());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testGetCompany() {
		try {
			assertNotNull(CompanyDAO.getCompany(CompanyDAO.getCompanies().get(0).getName()));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

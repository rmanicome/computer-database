package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;
import junit.framework.TestCase;

public class SQLCompanyQueryTest extends TestCase {
	
	public void testGetCompanies() {
		try {
			assertNotNull(SQLCompanyQuery.getCompanies());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testGetCompany() {
		try {
			assertNotNull(SQLCompanyQuery.getCompany(SQLCompanyQuery.getCompanies().get(0).getName()));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

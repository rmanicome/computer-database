package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

import junit.framework.TestCase;

public class SQLComputerQueryTest extends TestCase {
	
	public void testGetComputers(){
		try {
			ArrayList<Computer> compList = SQLComputerQuery.getComputers();
			assertNotNull(compList);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testAddComputer(){
		try {
			ArrayList<Computer> compList = SQLComputerQuery.getComputers();
			compList = SQLComputerQuery.getComputers();
			Computer comp = new Computer("un ordi");
			SQLComputerQuery.addComputer(comp);
			assertFalse(compList.size()==SQLComputerQuery.getComputers().size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testUpdateComputer(){
		try {
			ArrayList<Computer> compList = SQLComputerQuery.getComputers();
			compList = SQLComputerQuery.getComputers();
			Computer comp = compList.get(compList.size()-1);
			comp.setCompany(new Company("une company"));
			SQLComputerQuery.updateComputer(comp);
			compList = SQLComputerQuery.getComputers();
			assertFalse(compList==SQLComputerQuery.getComputers());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testDeleteComputer(){
		try {
			ArrayList<Computer> compList = SQLComputerQuery.getComputers();
			compList = SQLComputerQuery.getComputers();
			Computer comp = compList.get(compList.size()-1);
			SQLComputerQuery.deleteComputer(comp);
			assertFalse(compList.size()==SQLComputerQuery.getComputers().size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

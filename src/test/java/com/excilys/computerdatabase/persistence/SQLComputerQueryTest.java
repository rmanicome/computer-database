package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

import junit.framework.TestCase;

public class SQLComputerQueryTest extends TestCase {
	
	public void testGetComputers(){
		try {
			ArrayList<Computer> compList = ComputerDAO.getComputers();
			assertNotNull(compList);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testAddComputer(){
		try {
			ArrayList<Computer> compList = ComputerDAO.getComputers();
			compList = ComputerDAO.getComputers();
			Computer comp = new Computer("un ordi");
			ComputerDAO.addComputer(comp);
			assertFalse(compList.size()==ComputerDAO.getComputers().size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testUpdateComputer(){
		try {
			ArrayList<Computer> compList = ComputerDAO.getComputers();
			compList = ComputerDAO.getComputers();
			Computer comp = compList.get(compList.size()-1);
			comp.setCompany(new Company("une company"));
			ComputerDAO.updateComputer(comp);
			compList = ComputerDAO.getComputers();
			assertFalse(compList==ComputerDAO.getComputers());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testDeleteComputer(){
		try {
			ArrayList<Computer> compList = ComputerDAO.getComputers();
			compList = ComputerDAO.getComputers();
			Computer comp = compList.get(compList.size()-1);
			ComputerDAO.deleteComputer(comp);
			assertFalse(compList.size()==ComputerDAO.getComputers().size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

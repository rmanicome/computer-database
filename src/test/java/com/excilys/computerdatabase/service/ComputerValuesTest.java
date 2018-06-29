package com.excilys.computerdatabase.service;

import java.sql.SQLException;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

import junit.framework.TestCase;

public class ComputerValuesTest extends TestCase {
	
	public void testGetCompanyList() {
		ComputerValues comp = new ComputerValues();
		assertNotNull(comp.getComputerList());
	}
	
	public void testAddComputer() {
		ComputerValues comp = new ComputerValues();
		Integer size = comp.getComputerList().size();
		try {
			comp.addComputer(new Computer("un ordi"));
			assertFalse(size == comp.getComputerList().size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testUpdateComputer() {
		ComputerValues compList = new ComputerValues();
		Computer comp = compList.getComputerList().get(compList.getComputerList().size()-1);
		Computer oldComp = new Computer(comp.getId(), comp.getName(), comp.getIntroducedDate(), comp.getDiscountedDate(), comp.getCompany());
		comp.setCompany(new Company("une comapny"));
		try {
			compList.updateComputer(compList.getComputerList().size()-1, comp);
			assertFalse(oldComp.equals(compList.getComputerList().get(compList.getComputerList().size()-1)));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testDeleteComputer() {
		ComputerValues compList = new ComputerValues();
		Computer comp = compList.getComputerList().get(compList.getComputerList().size()-1);
		Integer size = compList.getComputerList().size();
		try {
			compList.deleteComputer(comp);
			assertFalse(size == compList.getComputerList().size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testDeleteWrongComputer() {
		ComputerValues compList = new ComputerValues();
		Integer size = compList.getComputerList().size();
		Computer comp = new Computer("Un ordi");
		try {
			compList.deleteComputer(comp);
			assertTrue(size == compList.getComputerList().size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

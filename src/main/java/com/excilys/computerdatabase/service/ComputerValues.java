package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.SQLComputerQuery;

public class ComputerValues {
	private ArrayList<Computer> computerList;
	
	public ComputerValues() {
		try {
			computerList = SQLComputerQuery.getComputers();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public ArrayList<Computer> getComputerList() {
		return computerList;
	}

	public void addComputer(Computer computer) throws SQLException {
		computerList.add(computer);
		SQLComputerQuery.addComputer(computer);
	}
	
	public void deleteComputer(Computer computer) throws SQLException {
		computerList.remove(computer);
		SQLComputerQuery.deleteComputer(computer);
	}
	
	public void updateComputer(Integer indexToUpdate, Computer newComputer) throws SQLException {
		computerList.set(indexToUpdate, newComputer);
		SQLComputerQuery.updateComputer(newComputer);
	}
}

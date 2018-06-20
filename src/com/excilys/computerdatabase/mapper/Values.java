package com.excilys.computerdatabase.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.SQLQuery;

public class Values {
	private ArrayList<Company> companyList;
	private ArrayList<Computer> computerList;
	
	public Values() throws SQLException {
		companyList = SQLQuery.getCompanies();
		computerList = SQLQuery.getComputers(); 
	}
	
	public ArrayList<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(ArrayList<Company> companyList) {
		this.companyList = companyList;
	}

	public ArrayList<Computer> getComputerList() {
		return computerList;
	}

	public void addComputer(Computer computer) throws SQLException {
		if(computer.getId() != null){
			Computer.setBiggestId(computer.getId());
		} else {
			Computer.setBiggestId(Computer.getBiggestId()+1);
			computer.setId(Computer.getBiggestId());
		}
		computerList.add(computer);
		SQLQuery.addComputer(computer);
	}
	
	public void deleteComputer(Computer computer) throws SQLException {
		computerList.remove(computer);
		SQLQuery.deleteComputer(computer);
	}
	
	public void updateComputer(Integer indexToUpdate, Computer newComputer) throws SQLException {
		computerList.set(indexToUpdate, newComputer);
		SQLQuery.updateComputer(newComputer);
	}
}

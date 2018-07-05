package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class ComputerService {
	private final static ComputerService INSTANCE = new ComputerService();
	private static ComputerDAO computerDAO = ComputerDAO.getInstance();
	
	private ComputerService() {
		
	}
	
	public static ComputerService getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<Computer> get() {
		return computerDAO.get();
	}
	
	public Optional<Computer> get(Integer id){
		return computerDAO.get(id);
	}

	public void add(Computer computer) {
		computerDAO.add(computer);
	}
	
	public void delete(Computer computer) {
		computerDAO.delete(computer);
	}
	
	public void update(Computer newComputer) {
		computerDAO.update(newComputer);
	}
}

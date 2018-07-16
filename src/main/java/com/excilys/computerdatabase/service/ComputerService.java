package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;


@Service
@Configuration
public class ComputerService {
	private final static ComputerService INSTANCE = new ComputerService();
	@Autowired
	private static ComputerDAO computerDAO;
	
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

package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

@Service
public class ComputerService {
	@Autowired
	private ComputerDAO computerDAO;
	
	public ArrayList<Computer> get() {
		return computerDAO.get();
	}
	
	public Optional<Computer> get(Long id){
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

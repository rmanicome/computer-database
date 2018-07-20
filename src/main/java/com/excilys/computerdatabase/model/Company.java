package com.excilys.computerdatabase.model;

public class Company {
	private Long id;
	private String name;
		
	public Company(String name) {
		this.name = name;
	}
	
	public Company(Long id, String name){
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Long getId(){
		return id;
	}
}

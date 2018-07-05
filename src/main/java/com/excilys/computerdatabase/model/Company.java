package com.excilys.computerdatabase.model;

public class Company {
	private Integer id;
	private String name;
		
	public Company(String name) {
		this.name = name;
	}
	
	public Company(Integer id, String name){
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Integer getId(){
		return id;
	}
}

package com.excilys.computerdatabase.model;

public class Company {
	private Integer id;
	private String name;
	
	private static Integer biggestId = 0;
	
	public Company(String name) {
		biggestId += 1;
		this.name = name;
		this.id = biggestId;
	}
	
	public Company(Integer id, String name){
		if(id > biggestId)
			biggestId = id;
		
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

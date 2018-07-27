package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	@Id
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	@Column(name="name")
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

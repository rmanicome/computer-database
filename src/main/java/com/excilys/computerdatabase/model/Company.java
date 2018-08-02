package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.excilys.computerdatabase.persistence.ConstantDB;

@Entity
@Table(name=ConstantDB.COMPANY_TABLE)
public class Company {
	@Id
	@GeneratedValue(generator="increment")
	@Column(name=ConstantDB.COMPANY_ID)
	private Long id;
	@Column(name=ConstantDB.COMPANY_NAME)
	private String name;
		
	public Company() {
		
	}
	
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

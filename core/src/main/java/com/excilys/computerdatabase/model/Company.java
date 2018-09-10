package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.excilys.computerdatabase.model.ConstantDB;

@Entity
@Table(name=ConstantDB.COMPANY_TABLE)
public class Company {
	@Id
	@GeneratedValue(generator="increment")
	@Column(name=ConstantDB.COMPANY_ID)
	private Long id;
	@Column(name=ConstantDB.COMPANY_NAME)
	private String name;
	@Column(name=ConstantDB.COMPANY_PICTURE)
	private String picture;
		
	public Company() {
		
	}
	
	public Company(String name, String picture) {
		this.name = name;
		this.picture = picture;
	}
	
	public Company(Long id, String name, String picture){
		this.id = id;
		this.name = name;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}
	
	public Long getId(){
		return id;
	}
	
	public String getPicture() {
		return picture;
	}
}

package com.excilys.computerdatabase.model;

import java.sql.Date;

public class Computer {
	private long id;
	private String name;
	private Date introducedDate;
	private Date discountedDate;
	private Company company;
	
	private static long biggestId = 0;
	
	public Computer(String name) {
		biggestId += 1;
		this.id = biggestId;
		this.name = name;
	}
	
	public Computer(long id, String name, Date introducedDate, Date discountedDate, Company company) {
		if(id > biggestId)
			biggestId = id;
		
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discountedDate = discountedDate;
		this.company = company;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}

	public Date getDiscountedDate() {
		return discountedDate;
	}

	public void setDiscountedDate(Date discountedDate) {
		this.discountedDate = discountedDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}	
	
	public static long getBiggestId(){
		return biggestId;
	}
	
	public static void setBiggestId(Integer biggestId) {
		if(Computer.biggestId < biggestId)
			Computer.biggestId = biggestId;
	}
}

package com.excilys.computerdatabase.model;

import java.sql.Date;

public class Computer {
	private Integer id;
	private String name;
	private Date introducedDate;
	private Date discountedDate;
	private Integer company;
	
	private static Integer biggestId;
	
	public Computer(String name){
		this.name = name;
	}
	
	public Computer(Integer id, String name, Date introducedDate, Date discountedDate, Integer company){
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discountedDate = discountedDate;
		this.company = company;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
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

	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}	
	
	public static Integer getBiggestId(){
		return biggestId;
	}
	
	public static void setBiggestId(Integer biggestId){
		if(Computer.biggestId < biggestId)
			Computer.biggestId = biggestId;
	}
}

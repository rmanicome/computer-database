package com.excilys.computerdatabase.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
	@Id
	@Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="introduced", nullable=true)
	private Date introducedDate;
	@Column(name="discontinued", nullable=true)
	private Date discontinuedDate;
	@Column(name="company_id")
	private Company company;
	
	public Computer(String name) {
		this.name = name;
	}
	
	public Computer(long id, String name, Date introducedDate, Date discountedDate, Company company) {
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discountedDate;
		this.company = company;
	}
	
	
	public static class ComputerBuilder {
		long id;
		String name;
		Date introducedDate;
		Date discontinuedDate;
		Company company;
		
		public ComputerBuilder(String name) {
			this.name = name;
		}
		
		public ComputerBuilder withName(String name) {
			this.name = name;
			return this;
		}
		
		public ComputerBuilder withId(long id){
			this.id = id;
			return this;
		}
		
		public ComputerBuilder withIntroducedDate(Date introduced){
			this.introducedDate = introduced;
			return this;
		}
		
		public ComputerBuilder withDiscountedDate(Date discontinued){
			this.discontinuedDate = discontinued;
			return this;
		}
		
		public ComputerBuilder withCompany(Company company){
			this.company = company;
			return this;
		}
		
		public Computer build(){
			return new Computer(id, name, introducedDate, discontinuedDate, company);
		}
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}

	public Date getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
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
}

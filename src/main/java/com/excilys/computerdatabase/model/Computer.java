package com.excilys.computerdatabase.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.excilys.computerdatabase.persistence.ConstantDB;

@Entity
@Table(name=ConstantDB.COMPUTER_TABLE)
public class Computer {
	@Id
	@GeneratedValue(generator="increment")
	@Column(name=ConstantDB.COMPUTER_ID)
	private long id;
	@Column(name=ConstantDB.COMPUTER_NAME)
	private String name;
	@Column(name=ConstantDB.COMPUTER_INTRODUCED, nullable=true)
	private LocalDate introducedDate;
	@Column(name=ConstantDB.COMPUTER_DISCONTINUED, nullable=true)
	private LocalDate discontinuedDate;
	@ManyToOne
	@JoinColumn(name=ConstantDB.COMPUTER_COMPANY_ID)
	private Company company;
	
	public Computer() {

	}
	
	public Computer(String name) {
		this.name = name;
	}
	
	public Computer(long id, String name, LocalDate introducedDate, LocalDate discountedDate, Company company) {
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discountedDate;
		this.company = company;
	}
	
	
	public static class ComputerBuilder {
		long id;
		String name;
		LocalDate introducedDate;
		LocalDate discontinuedDate;
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
		
		public ComputerBuilder withIntroducedDate(LocalDate introduced){
			this.introducedDate = introduced;
			return this;
		}
		
		public ComputerBuilder withDiscountedDate(LocalDate discontinued){
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
	
	public LocalDate getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDate introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDate discontinuedDate) {
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

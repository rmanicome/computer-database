package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.configuration.HibernateConfiguration;
import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO {
	private final static String GET = "SELECT "+ConstantBD.COMPANY_ID+","+ConstantBD.COMPANY_NAME+" FROM "+ConstantBD.COMPANY_TABLE+";";
	private final static String GET_BY_NAME = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_NAME+" = :name;"; 
	private final static String GET_BY_ID = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = :id;";
	private final static String DELETE = "DELETE FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = :id;";
	private final static String DELETE_COMPUTER = "DELETE FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_COMPANY_ID+" = :id;";
	
	public ArrayList<Company> get() {
		Session session = HibernateConfiguration.getSession();
		Query<Company> query = session.createQuery(GET);
		
		return (ArrayList<Company>) query.list();
	}
	
	public Optional<Company> get(String name) {
		Session session = HibernateConfiguration.getSession();
		Query<Company> query = session.createQuery(GET_BY_NAME);
		query.setParameter("name", name);
		
		return query.uniqueResultOptional();
		
	}
	
	public Optional<Company> get(Long id) {
		Session session = HibernateConfiguration.getSession();
		Query<Company> query = session.createQuery(GET_BY_ID);
		query.setParameter("id", id);
		
		return query.uniqueResultOptional();
	}
	
	public void delete(Long id) {
		Session session = HibernateConfiguration.getSession();
		Query<Company> query = session.createQuery(DELETE);
		query.setParameter("id", id);
		Query<Company> queryComputer = session.createQuery(DELETE_COMPUTER);
		queryComputer.setParameter("id", id);
		
		queryComputer.executeUpdate();
		query.executeUpdate();
	}
}

package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.configuration.HibernateConfiguration;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

@Repository
public class CompanyDAO {
	private final static SessionFactory SESSION_FACTORY = HibernateConfiguration.getSessionFactory();
	
	private final static String GET = "SELECT "+ConstantDB.COMPANY_ID+","+ConstantDB.COMPANY_NAME+" FROM Company";
	private final static String GET_BY_NAME = "SELECT * FROM Company WHERE "+ConstantDB.COMPANY_NAME+" = ?"; 
	private final static String GET_BY_ID = "SELECT * FROM Company WHERE "+ConstantDB.COMPANY_ID+" = ?";
	private final static String DELETE = "DELETE FROM Company WHERE "+ConstantDB.COMPANY_ID+" = ?";
	private final static String DELETE_COMPUTER = "DELETE FROM Computer WHERE "+ConstantDB.COMPUTER_COMPANY_ID+" = ?";
	
	public ArrayList<Company> get() {
		Session session = SESSION_FACTORY.openSession();
		
		Query<Company> query = session.createQuery(GET, Company.class);
		ArrayList<Company> companyList = (ArrayList<Company>) query.getResultList();
		session.close();
		
		return companyList;
	}
	
	public Optional<Company> get(String name) {
		Session session = SESSION_FACTORY.openSession();
		
		Query<Company> query = session.createQuery(GET_BY_NAME, Company.class);
		query.setParameter(0, name);
		Company company = query.getSingleResult();
		session.close();
		
		return Optional.ofNullable(company);
	}
	
	public Optional<Company> get(Long id) {
		Session session = SESSION_FACTORY.openSession();
		
		Query<Company> query = session.createQuery(GET_BY_ID, Company.class);
		query.setParameter(0, id);
		Company company = query.getSingleResult();
		session.close();
		
		return Optional.ofNullable(company);
	}
	
	public void delete(Long id) {
		Session session = SESSION_FACTORY.openSession();
		
		Query<Computer> queryComputer = session.createQuery(DELETE_COMPUTER, Computer.class);
		queryComputer.setParameter(0, id);
		Query<Company> queryCompany = session.createQuery(DELETE, Company.class);
		queryCompany.setParameter(0, id);
		
		queryComputer.executeUpdate();
		queryCompany.executeUpdate();
		
		session.close();
	}
}

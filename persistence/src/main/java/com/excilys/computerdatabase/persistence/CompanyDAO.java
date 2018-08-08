package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.configuration.HibernateConfiguration;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ConstantDB;

@Repository
public class CompanyDAO {
	private final static SessionFactory SESSION_FACTORY = HibernateConfiguration.getSessionFactory();
	
	private final static String GET = "FROM Company";
	private final static String GET_BY_NAME = "FROM Company WHERE "+ConstantDB.COMPANY_NAME+" = :name"; 
	private final static String GET_BY_ID = "FROM Company WHERE "+ConstantDB.COMPANY_ID+" = :id";
	private final static String DELETE_COMPUTER = "DELETE FROM "+ConstantDB.COMPUTER_TABLE+" WHERE "+ConstantDB.COMPUTER_COMPANY_ID+" = :id";
	
	public ArrayList<Company> get() {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Company> query = session.createQuery(GET, Company.class);
		ArrayList<Company> companyList = (ArrayList<Company>) query.getResultList();
		session.close();
		
		return companyList;
	}
	
	public Optional<Company> get(String name) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Company> query = session.createQuery(GET_BY_NAME, Company.class);
		query.setParameter("name", name);
		Company company = query.getSingleResult();
		session.close();
		
		return Optional.ofNullable(company);
	}
	
	public Optional<Company> get(Long id) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Company> query = session.createQuery(GET_BY_ID, Company.class);
		query.setParameter("id", id);
		Company company = query.getSingleResult();
		session.close();
		
		return Optional.ofNullable(company);
	}
	
	@Transactional
	public void delete(Company comp) {
		Session session = SESSION_FACTORY.openSession();
		Query<Computer> query = session.createQuery(DELETE_COMPUTER);
		query.setParameter("id", comp.getId());
		query.executeUpdate();
		session.beginTransaction();
		session.delete(comp);
		session.getTransaction().commit();
		session.close();
	}
}

package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.configuration.HibernateConfiguration;
import com.excilys.computerdatabase.model.Computer;

@Repository
public class ComputerDAO {
	private final static SessionFactory SESSION_FACTORY = HibernateConfiguration.getSessionFactory();
	
	private final static String GET_LIST = "FROM Computer";
	private final static String GET_BY_ID = "FROM Computer WHERE "+ConstantDB.COMPUTER_ID+"= :id";

	public ArrayList<Computer> get(){
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Computer> query = session.createQuery(GET_LIST, Computer.class);
		ArrayList<Computer> computerList = (ArrayList<Computer>) query.getResultList();
		session.close();
		
		return computerList;
	}
	
	public Optional<Computer> get(Long id) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Computer> query = session.createQuery(GET_BY_ID, Computer.class);
		query.setParameter("id", id);
		Computer computer = query.getSingleResult();
		session.close();
		
		return Optional.ofNullable(computer);
	}

	public void add(Computer comp) {
		Session session = SESSION_FACTORY.openSession();
		session.beginTransaction();
		session.save(comp);
		session.getTransaction().commit();
		session.close();
	}

	@Transactional
	public void update(Computer comp) {
		Session session = SESSION_FACTORY.openSession();
		session.beginTransaction();
		session.update(comp);
		session.getTransaction().commit();
		session.close();
	}

	@Transactional
	public void delete(Computer comp) {
		Session session = SESSION_FACTORY.openSession();
		session.beginTransaction();
		session.delete(comp);
		session.getTransaction().commit();
		session.close();
	}

}

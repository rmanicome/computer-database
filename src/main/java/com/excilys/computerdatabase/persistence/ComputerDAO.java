package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.configuration.HibernateConfiguration;
import com.excilys.computerdatabase.model.Computer;

@Repository
public class ComputerDAO {
	private final static SessionFactory SESSION_FACTORY = HibernateConfiguration.getSessionFactory();
	
	private final static String GET_LIST = 
//			"SELECT Computer."+ConstantDB.COMPUTER_ID+","
//					+"Computer."+ConstantDB.COMPUTER_NAME+","
//					+"Computer."+ConstantDB.COMPUTER_INTRODUCED+","
//					+"Computer."+ConstantDB.COMPUTER_DISCONTINUED+","
//					+"Computer."+ConstantDB.COMPUTER_COMPANY_ID+
			" FROM Computer";
	private final static String GET_BY_ID = 
//			"SELECT Computer."+ConstantDB.COMPUTER_ID+","
//					+"Computer."+ConstantDB.COMPUTER_NAME+","
//					+"Computer."+ConstantDB.COMPUTER_INTRODUCED+","
//					+"Computer."+ConstantDB.COMPUTER_DISCONTINUED+","
//					+"Computer."+ConstantDB.COMPUTER_COMPANY_ID+
			" FROM Computer WHERE Computer."+ConstantDB.COMPUTER_ID+"= ?";
	private final static String ADD = 
			"INSERT INTO Computer ("
					+"Computer."+ConstantDB.COMPUTER_NAME+","
					+"Computer."+ConstantDB.COMPUTER_INTRODUCED+","
					+"Computer."+ConstantDB.COMPUTER_DISCONTINUED+","
					+"Computer."+ConstantDB.COMPUTER_COMPANY_ID+") "
			+ "values (?, ?, ?, ?)";
	private final static String UPDATE = 
			"UPDATE Computer SET Computer."+ConstantDB.COMPUTER_NAME+" = ?, "
					+"Computer."+ConstantDB.COMPUTER_INTRODUCED+" = ?, "
					+"Computer."+ConstantDB.COMPUTER_DISCONTINUED+" = ?, "
					+"Computer."+ConstantDB.COMPUTER_COMPANY_ID+" = ? WHERE Computer."+ConstantDB.COMPUTER_ID+" = ?";
	private final static String DELETE = "DELETE FROM Computer WHERE Computer."+ConstantDB.COMPUTER_ID+" = ?";

	public ArrayList<Computer> get(){
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Computer> query = session.createQuery(GET_LIST, Computer.class);
		ArrayList<Computer> computerList = (ArrayList<Computer>) query.getResultList();
		session.close();
		
		return computerList;
	}
	
	public Optional<Computer> get(Integer id) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Computer> query = session.createQuery(GET_BY_ID, Computer.class);
		query.setParameter(0, id);
		Computer computer = query.getSingleResult();
		session.close();
		
		return Optional.ofNullable(computer);
	}

	public void add(Computer comp) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Computer> query = session.createQuery(ADD, Computer.class);
		query.setParameter(0, comp.getName());
		query.setParameter(1, comp.getIntroducedDate());
		query.setParameter(2, comp.getDiscontinuedDate());
		query.setParameter(3, comp.getCompany().getId());
		query.executeUpdate();
		
		session.close();
	}

	public void update(Computer comp) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Computer> query = session.createQuery(UPDATE, Computer.class);
		query.setParameter(0, comp.getName());
		query.setParameter(1, comp.getIntroducedDate());
		query.setParameter(2, comp.getDiscontinuedDate());
		query.setParameter(3, comp.getCompany().getId());
		query.setParameter(4, comp.getId());
		query.executeUpdate();
		
		session.close();
	}

	public void delete(Computer comp) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<Computer> query = session.createQuery(DELETE, Computer.class);
		query.setParameter(0, comp.getId());
		query.executeUpdate();
		
		session.close();
	}

}

package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Computer;

@Repository
public class ComputerDAO {
	@Autowired
	private Session session;
	
	private final static String GET_LIST = 
			"SELECT "+ConstantBD.COMPUTER_ID+","
					+ConstantBD.COMPUTER_NAME+","
					+ConstantBD.COMPUTER_INTRODUCED+","
					+ConstantBD.COMPUTER_DISCONTINUED+","
					+ConstantBD.COMPUTER_COMPANY_ID+
			" FROM "+ConstantBD.COMPUTER_TABLE+";";
	private final static String GET_BY_ID = 
			"SELECT "+ConstantBD.COMPUTER_ID+","
					+ConstantBD.COMPUTER_NAME+","
					+ConstantBD.COMPUTER_INTRODUCED+","
					+ConstantBD.COMPUTER_DISCONTINUED+","
					+ConstantBD.COMPUTER_COMPANY_ID+
			" FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_ID+"= :id;";
	private final static String ADD = 
			"INSERT INTO "+ConstantBD.COMPUTER_TABLE+" ("
					+ConstantBD.COMPUTER_NAME+","
					+ConstantBD.COMPUTER_INTRODUCED+","
					+ConstantBD.COMPUTER_DISCONTINUED+","
					+ConstantBD.COMPUTER_COMPANY_ID+") "
			+ "values (:name, :introduced, :discontinued, :company);";
	private final static String UPDATE = 
			"UPDATE "+ConstantBD.COMPUTER_TABLE+
			" SET "+ConstantBD.COMPUTER_NAME+" = :name, "
					+ConstantBD.COMPUTER_INTRODUCED+" = :introduced, "
					+ConstantBD.COMPUTER_DISCONTINUED+" = :discontinued, "
					+ConstantBD.COMPUTER_COMPANY_ID+" = :company WHERE "+ConstantBD.COMPUTER_ID+" = :id;";
	private final static String DELETE = "DELETE FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_ID+" = :id;";

	public ArrayList<Computer> get(){
		Query<Computer> query = session.createQuery(GET_LIST);
		
		return (ArrayList<Computer>) query.getResultList();
	}
	
	public Optional<Computer> get(Integer id) {
		Query<Computer> query = session.createQuery(GET_BY_ID);
		query.setParameter("id", id);
		
		return query.uniqueResultOptional(); 
	}

	public void add(Computer comp) {
		Query<Computer> query = session.createQuery(ADD);
		query.setParameter("name", comp.getName());
		query.setParameter("introduced", comp.getIntroducedDate());
		query.setParameter("discontinued", comp.getDiscontinuedDate());
		query.setParameter("company", comp.getCompany().getId());
		
		query.executeUpdate();
	}

	public void update(Computer comp) {
		Query<Computer> query = session.createQuery(UPDATE);
		query.setParameter("name", comp.getName());
		query.setParameter("introduced", comp.getIntroducedDate());
		query.setParameter("discontinued", comp.getDiscontinuedDate());
		query.setParameter("company", comp.getCompany().getId());
		query.setParameter("id", comp.getId());
		
		query.executeUpdate();
	}

	public void delete(Computer comp) {
		Query<Computer> query = session.createQuery(DELETE);
		query.setParameter("id", comp.getId());
		
		query.executeUpdate();
	}

}

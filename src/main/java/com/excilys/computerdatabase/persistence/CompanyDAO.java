package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.configuration.HibernateConfiguration;
import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO {
	
	public ArrayList<Company> get() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ConnectionPool.getDataSource());
		ArrayList<Company> companyList = new ArrayList<Company>();
		
		for (Map<String, Object> company : jdbcTemplate.queryForList("SELECT "+ConstantBD.COMPANY_ID+","+ConstantBD.COMPANY_NAME+" FROM "+ConstantBD.COMPANY_TABLE+";")) {
			companyList.add(new Company((Long) company.get(ConstantBD.COMPANY_ID), (String) company.get(ConstantBD.COMPANY_NAME)));
		}
		
		return companyList;
		/*Session session = HibernateConfiguration.getSession();
		
		session.beginTransaction();
		Query<Company> query = session.createQuery("from company");
		
		ArrayList<Company> companyList = new ArrayList<Company>();
		
		System.out.println(query.getQueryString());
		/*for (Company company : query.getResultList()) {
			companyList.add(company);
		}*/
		
		//return companyList;
	}
	
	public Optional<Company> get(String name) {
		Session session = HibernateConfiguration.getSession();
		
		session.beginTransaction();
		Query<Company> query = session.createQuery("from company where name= :name");
		query.setParameter("name", name);
		
		return query.uniqueResultOptional();
		
	}
	
	public Optional<Company> get(Long id) {
		Session session = HibernateConfiguration.getSession();
		
		session.beginTransaction();
		Query<Company> query = session.createQuery("from company where name= :id");
		query.setParameter("id", id);
		
		return query.uniqueResultOptional();
	}
	
	public void delete(Long id) {
		/*try {
			jdbcTemplate.getDataSource().getConnection().setAutoCommit(false);
			jdbcTemplate.update(DELETE, id);
			jdbcTemplate.update(DELETE_COMPUTER, id);
			jdbcTemplate.getDataSource().getConnection().commit();
			jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			logger.error("Erreur lors de la deletion de companie. Une erreur est intervenue lors de l'acces à la base de données");
		}*/
	}
}

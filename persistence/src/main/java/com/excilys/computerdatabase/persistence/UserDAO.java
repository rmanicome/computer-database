package com.excilys.computerdatabase.persistence;

import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.configuration.HibernateConfiguration;
import com.excilys.computerdatabase.model.ConstantDB;
import com.excilys.computerdatabase.model.User;

@Repository
public class UserDAO {
	private static final SessionFactory SESSION_FACTORY = HibernateConfiguration.getSessionFactory();
	
	private final static String GET = "FROM User WHERE "+ConstantDB.USER_NAME+" = :name";
	
	public Optional<User> get(String name) {
		Session session = SESSION_FACTORY.openSession();
		
		TypedQuery<User> query = session.createQuery(GET, User.class);
		query.setParameter("name", name);
		User user = query.getSingleResult();
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		session.close();
		
		return Optional.ofNullable(user);
	}
}

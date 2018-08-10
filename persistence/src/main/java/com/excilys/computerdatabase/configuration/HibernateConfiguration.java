package com.excilys.computerdatabase.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.configuration.ConfigDB;

public class HibernateConfiguration {
	static SessionFactory sessionFactory ;
	
	static {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass (Computer.class);
		configuration.addAnnotatedClass (Company.class);
		configuration.addAnnotatedClass(User.class);
		configuration.setProperty("hibernate.connection.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");
		configuration.setProperty("hibernate.connection.driver_class", ConfigDB.DRIVER);
		configuration.setProperty("hibernate.connection.url", ConfigDB.URL);                                
		configuration.setProperty("hibernate.connection.username", ConfigDB.USER_NAME);     
		configuration.setProperty("hibernate.connection.password", ConfigDB.PASSWORD);
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("hibernate.show_sql", "false");
		configuration.setProperty("hibernate.connection.pool_size", "10");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

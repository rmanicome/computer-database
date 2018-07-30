package com.excilys.computerdatabase.configuration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.context.annotation.Bean;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class HibernateConfiguration {
	private static final SessionFactory sessionFactory;
	
    static {
        try {
            sessionFactory = new AnnotationConfiguration()
            		.addPackage("com.excilys.computerdatabase.model")
            		.addAnnotatedClass(Company.class)
            		.addAnnotatedClass(Computer.class)
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    @Bean
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}

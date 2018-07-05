package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connexion {
	final static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	private final static Connexion INSTANCE = new Connexion();
	private static Connection con;
	
	private Connexion() {
		String url = "jdbc:mysql://localhost:3306/computer-database-db";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}
	
	public static Connexion getInstance() {
		return INSTANCE;
	}
	
	public Connection getConnection() {
		return con;
	}
}

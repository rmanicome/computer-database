package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private final static Connexion INSTANCE = new Connexion();
	private static Connection con;
	
	private Connexion() {
		String url = "jdbc:mysql://localhost:3306/computer-database-db";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la connection : "+e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur lors de la connection sur le driver : "+e.getMessage());
		}
	}
	
	public static Connexion getInstance() {
		return INSTANCE;
	}
	
	public Connection getConnection() {
		return con;
	}
}

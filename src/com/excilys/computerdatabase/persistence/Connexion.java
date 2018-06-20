package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private final static Connexion INSTANCE = new Connexion();
	private static Connection con;
	
	private Connexion(){
		String url = "jdbc:mysql://localhost:3306/computer-database-db";
		try {
			con = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la connection");
		}
	}
	
	public static Connexion getInstance(){
		return INSTANCE;
	}
	
	public Connection getConnection(){
		return con;
	}
}

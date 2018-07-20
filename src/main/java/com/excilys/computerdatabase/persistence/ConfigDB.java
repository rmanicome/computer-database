package com.excilys.computerdatabase.persistence;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConfigDB {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/computer-database-db";
	public static final String USER_NAME = "admincdb";
	public static final String PASSWORD = "qwerty1234";
	
	public static DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USER_NAME);
		dataSource.setPassword(PASSWORD);
		
		return dataSource;
	}
}

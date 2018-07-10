package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {
	final static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
 
    static {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
	        config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
	        config.setUsername( "admincdb" );
	        config.setPassword( "qwerty1234" );
	        config.addDataSourceProperty( "cachePrepStmts" , "true" );
	        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
	        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
	        ds = new HikariDataSource( config );
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
    }
 
    private ConnectionPool() {}
 
    public static Connection getConnection() {
        try {
			return ds.getConnection();
		} catch (SQLException e) {
			logger.error("Unable to get the connection : " + e.getMessage());
	        return null;
		}
    }
}

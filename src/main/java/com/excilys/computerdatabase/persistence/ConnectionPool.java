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
			Class.forName(ConfigDB.DRIVER);
	        config.setJdbcUrl( ConfigDB.URL );
	        config.setUsername( ConfigDB.USER_NAME );
	        config.setPassword( ConfigDB.PASSWORD );
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

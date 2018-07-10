package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.computerdatabase.service.CompanyService;

public class ComputerDAO {
	private final static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private final static ComputerDAO INSTANCE = new ComputerDAO();
	
	private final static String VARIABLE_1 = "variable1";
	private final static String VARIABLE_2 = "variable2";
	private final static String VARIABLE_3 = "variable3";
	private final static String VARIABLE_4 = "variable4";
	private final static String VARIABLE_5 = "variable5";
	
	private final static String GET_LIST = 
			"SELECT "+ConstantBD.COMPUTER_ID+","
					+ConstantBD.COMPUTER_NAME+","
					+ConstantBD.COMPUTER_INTRODUCED+","
					+ConstantBD.COMPUTER_DISCONTINUED+","
					+ConstantBD.COMPUTER_COMPANY_ID+
			" FROM "+ConstantBD.COMPUTER_TABLE+";";
	private final static String GET_BY_ID = 
			"SELECT "+ConstantBD.COMPUTER_ID+","
					+ConstantBD.COMPUTER_NAME+","
					+ConstantBD.COMPUTER_INTRODUCED+","
					+ConstantBD.COMPUTER_DISCONTINUED+","
					+ConstantBD.COMPUTER_COMPANY_ID+
			" FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_ID+"= '"+VARIABLE_1+"';";
	private final static String ADD = 
			"INSERT INTO "+ConstantBD.COMPUTER_TABLE+" ("
					+ConstantBD.COMPUTER_ID+","
					+ConstantBD.COMPUTER_NAME+","
					+ConstantBD.COMPUTER_INTRODUCED+","
					+ConstantBD.COMPUTER_DISCONTINUED+","
					+ConstantBD.COMPUTER_COMPANY_ID+") "
			+ "values ('"+VARIABLE_1+"','"+VARIABLE_2+"',"+VARIABLE_3+","+VARIABLE_4+","+VARIABLE_5+");";
	private final static String UPDATE = 
			"UPDATE "+ConstantBD.COMPUTER_TABLE+
			" SET "+ConstantBD.COMPUTER_NAME+" = '"+VARIABLE_1+"', "+ConstantBD.COMPUTER_INTRODUCED+" = "+VARIABLE_2+", "
					+ConstantBD.COMPUTER_DISCONTINUED+" = "+VARIABLE_3+", "
					+ConstantBD.COMPUTER_COMPANY_ID+" = "+VARIABLE_4+
					" WHERE "+ConstantBD.COMPUTER_ID+" = '"+VARIABLE_5+"';";
	private final static String DELETE = "DELETE FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_ID+" = "+VARIABLE_1+";";
	
	private ComputerDAO(){
		
	}
	
	public static ComputerDAO getInstance(){
		return INSTANCE;
	}
	
	public ArrayList<Computer> get(){
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		
		try (Connection connexion = ConnectionPool.getConnection()){
			CompanyService companyService = CompanyService.getInstance();
			Statement stmt = connexion.createStatement();
			ResultSet computers;
			
			computers = stmt.executeQuery(GET_LIST);
			while(computers.next()){
				computerList.add(new ComputerBuilder(computers.getString(2))
						.withId(computers.getLong(1))
						.withIntroducedDate(computers.getDate(3))
						.withDiscountedDate(computers.getDate(4))
						.withCompany(companyService.get(computers.getInt(5)).orElse(null)).build());
			}
			
			stmt.close();
			
			return computerList;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return computerList;
		}
	}
	
	public Optional<Computer> get(Integer id) {
		try (Connection connexion = ConnectionPool.getConnection()){
			CompanyService companyService = CompanyService.getInstance();
			ResultSet computer;
			
			Statement stmt = connexion.createStatement();
			computer = stmt.executeQuery(GET_BY_ID.replaceFirst(VARIABLE_1, id.toString()));
			computer.next();
			
			return Optional.of(new ComputerBuilder(computer.getString(2))
					.withId(computer.getLong(1))
					.withIntroducedDate(computer.getDate(3))
					.withDiscountedDate(computer.getDate(4))
					.withCompany(companyService.get(computer.getInt(5)).orElse(null)).build());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return Optional.empty();
		}
	}

	public void add(Computer comp) {
		try (Connection connexion = ConnectionPool.getConnection()){
			Statement stmt = connexion.createStatement();
			
			String newQuery = ADD.toString();
			newQuery = newQuery.replace(VARIABLE_1,String.valueOf(comp.getId()));
			newQuery = newQuery.replace(VARIABLE_2, comp.getName());
			newQuery = newQuery.replace(VARIABLE_3, (comp.getIntroducedDate() == null ? "null" : "'"+comp.getIntroducedDate()+"'"));
			newQuery = newQuery.replace(VARIABLE_4,(comp.getDiscontinuedDate() == null ? "null" : "'"+comp.getDiscontinuedDate()+"'"));
			newQuery = newQuery.replace(VARIABLE_5,(comp.getCompany() == null ? "null" : comp.getCompany().getId().toString()));
			stmt.executeUpdate(newQuery);
			
			stmt.close();
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public void update(Computer comp) {
		try (Connection connexion = ConnectionPool.getConnection()){
			Statement stmt = connexion.createStatement();
			String newQuery = UPDATE.toString();
			newQuery = newQuery.replace(VARIABLE_5,String.valueOf(comp.getId()));
			newQuery = newQuery.replace(VARIABLE_1, comp.getName());
			newQuery = newQuery.replace(VARIABLE_2, (comp.getIntroducedDate() == null ? "null" : "'"+comp.getIntroducedDate()+"'"));
			newQuery = newQuery.replace(VARIABLE_3,(comp.getDiscontinuedDate() == null ? "null" : "'"+comp.getDiscontinuedDate()+"'"));
			newQuery = newQuery.replace(VARIABLE_4,(comp.getCompany() == null ? "null" : comp.getCompany().getId().toString()));
			stmt.executeUpdate(newQuery);
			
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public void delete(Computer comp) {
		try (Connection connexion = ConnectionPool.getConnection()){
			Statement stmt = connexion.createStatement();
			
			stmt.executeUpdate(DELETE.replaceFirst(VARIABLE_1, String.valueOf(comp.getId())));
			
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

}

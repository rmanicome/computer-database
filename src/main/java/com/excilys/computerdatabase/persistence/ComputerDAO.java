package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.computerdatabase.service.CompanyService;

@Repository
public class ComputerDAO {
	private final static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	@Autowired
	private CompanyService companyService;
	
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
			" FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_ID+"= ?;";
	private final static String ADD = 
			"INSERT INTO "+ConstantBD.COMPUTER_TABLE+" ("
					+ConstantBD.COMPUTER_ID+","
					+ConstantBD.COMPUTER_NAME+","
					+ConstantBD.COMPUTER_INTRODUCED+","
					+ConstantBD.COMPUTER_DISCONTINUED+","
					+ConstantBD.COMPUTER_COMPANY_ID+") "
			+ "values (?, ?, ?, ?, ?);";
	private final static String UPDATE = 
			"UPDATE "+ConstantBD.COMPUTER_TABLE+
			" SET "+ConstantBD.COMPUTER_NAME+" = ?, "
					+ConstantBD.COMPUTER_INTRODUCED+" = ?, "
					+ConstantBD.COMPUTER_DISCONTINUED+" = ?, "
					+ConstantBD.COMPUTER_COMPANY_ID+" = ? WHERE "+ConstantBD.COMPUTER_ID+" = ?;";
	private final static String DELETE = "DELETE FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_ID+" = ?;";

	public ArrayList<Computer> get(){
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		
		try (Connection connexion = ConnectionPool.getConnection()){
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
			ResultSet computer;
			
			PreparedStatement query = connexion.prepareStatement(GET_BY_ID);
			query.setInt(1, id);
			computer = query.executeQuery();
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
			PreparedStatement query = connexion.prepareStatement(ADD);
			query.setInt(1, (int) comp.getId());
			query.setString(2, comp.getName());
			query.setDate(3, comp.getIntroducedDate());
			query.setDate(4, comp.getDiscontinuedDate());
			if(comp.getCompany() != null)
				query.setInt(5,comp.getCompany().getId());
			else
				query.setNull(5, 0);
			query.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public void update(Computer comp) {
		try (Connection connexion = ConnectionPool.getConnection()){
			PreparedStatement query = connexion.prepareStatement(UPDATE);
			query.setString(1, comp.getName());
			query.setDate(2, comp.getIntroducedDate());
			query.setDate(3, comp.getDiscontinuedDate());
			if(comp.getCompany() == null)
				query.setNull(4, 0); 
			else
				query.setInt(4, comp.getCompany().getId());
			query.setInt(5, (int) comp.getId());
			query.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public void delete(Computer comp) {
		try (Connection connexion = ConnectionPool.getConnection()){
			PreparedStatement query = connexion.prepareStatement(DELETE);
			query.setInt(1, (int) comp.getId());
			query.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

}

package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Constant;

public class SQLComputerQuery {
	private static Connection connexion = Connexion.getInstance().getConnection();

	public static ArrayList<Computer> getComputers() throws SQLException {
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		String computerQuery = "SELECT * FROM "+Constant.COMPUTER_TABLE+";";
		ResultSet computers;

		Statement stmt = connexion.createStatement();
		computers = stmt.executeQuery(computerQuery);

		while(computers.next()){
			computerList.add(new Computer(computers.getInt(1), computers.getString(2), computers.getDate(3), computers.getDate(4), SQLCompanyQuery.getCompany(computers.getInt(5))));
		}

		return computerList;
	}

	public static void addComputer(Computer comp) throws SQLException {
		String computerQuery = "INSERT INTO "+Constant.COMPUTER_TABLE+" ("+Constant.COMPUTER_ID+","+Constant.COMPUTER_NAME+","+Constant.COMPUTER_INTRODUCED+","+Constant.COMPUTER_DISCONTINUED+","+Constant.COMPUTER_COMPANY_ID+") "
				+ "values ('"+comp.getId()+"','"+comp.getName()+"',"+
				(comp.getIntroducedDate() == null ? comp.getIntroducedDate() : "'"+comp.getIntroducedDate()+"'")+","+
				(comp.getDiscountedDate() == null ? comp.getDiscountedDate() : "'"+comp.getDiscountedDate()+"'")+","+
				comp.getCompany()+");";

		Statement stmt = connexion.createStatement();
		stmt.executeUpdate(computerQuery);
	}

	public static void updateComputer(Computer comp) throws SQLException {
		String computerQuery = "UPDATE "+Constant.COMPUTER_TABLE+" SET "+Constant.COMPUTER_NAME+" = '"+comp.getName()+"', "+Constant.COMPUTER_INTRODUCED+" = "+
				(comp.getIntroducedDate() == null ? comp.getIntroducedDate() : "'"+comp.getIntroducedDate()+"'")+", "+Constant.COMPUTER_DISCONTINUED+" = "+
				(comp.getDiscountedDate() == null ? comp.getDiscountedDate() : "'"+comp.getDiscountedDate()+"'")+", "+Constant.COMPUTER_COMPANY_ID+" = "+
				comp.getCompany()+" WHERE "+Constant.COMPUTER_ID+" = '"+comp.getId()+"';";

		Statement stmt = connexion.createStatement();
		stmt.executeUpdate(computerQuery);
	}

	public static void deleteComputer(Computer comp) throws SQLException {
		String computerQuery = "DELETE FROM "+Constant.COMPUTER_TABLE+" WHERE "+Constant.COMPUTER_ID+" = '"+comp.getId()+"';";

		Statement stmt = connexion.createStatement();
		stmt.executeUpdate(computerQuery);
	}

}

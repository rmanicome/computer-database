package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(ConnectionPool.getDataSource());
	
	private final static String GET = "SELECT "+ConstantBD.COMPANY_ID+","+ConstantBD.COMPANY_NAME+" FROM "+ConstantBD.COMPANY_TABLE+";";
	private final static String GET_BY_NAME = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_NAME+" = ?;"; 
	private final static String GET_BY_ID = "SELECT * FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = ?;";
	//private final static String DELETE = "DELETE FROM "+ConstantBD.COMPANY_TABLE+" WHERE "+ConstantBD.COMPANY_ID+" = ?;";
	//private final static String DELETE_COMPUTER = "DELETE FROM "+ConstantBD.COMPUTER_TABLE+" WHERE "+ConstantBD.COMPUTER_COMPANY_ID+" = ?;";
	
	public ArrayList<Company> get() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		
		for (Map<String, Object> company : jdbcTemplate.queryForList(GET)) {
			companyList.add(new Company((Long) company.get(ConstantBD.COMPANY_ID), (String) company.get(ConstantBD.COMPANY_NAME)));
		}
		
		return companyList;
	}
	
	public Optional<Company> get(String name) {
		Map<String, Object> result = jdbcTemplate.queryForMap(GET_BY_NAME, name);
		 
		if(result.isEmpty())
			return Optional.empty();
		
		return Optional.of(new Company((Long) result.get(ConstantBD.COMPANY_ID),(String) result.get(ConstantBD.COMPANY_NAME)));
	}
	
	public Optional<Company> get(Long id) {
		if(id == null)
			return Optional.empty();
		
		Map<String, Object> result = jdbcTemplate.queryForMap(GET_BY_ID, id);
		
		if(result.isEmpty())
			return Optional.empty();
		
		return Optional.of(new Company((Long) result.get(ConstantBD.COMPANY_ID),(String) result.get(ConstantBD.COMPANY_NAME)));
	}
}

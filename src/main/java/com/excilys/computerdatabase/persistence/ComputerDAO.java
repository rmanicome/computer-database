package com.excilys.computerdatabase.persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.computerdatabase.service.CompanyService;

@Repository
public class ComputerDAO {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(ConfigDB.getDataSource());
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
		
		for (Map<String, Object> computer : jdbcTemplate.queryForList(GET_LIST)) {
			computerList.add(new ComputerBuilder((String) computer.get(ConstantBD.COMPANY_NAME))
					.withId((Long) computer.get(ConstantBD.COMPUTER_ID))
					.withIntroducedDate((Date) computer.get(ConstantBD.COMPUTER_INTRODUCED))
					.withDiscountedDate((Date) computer.get(ConstantBD.COMPUTER_DISCONTINUED))
					.withCompany(computer.get(ConstantBD.COMPUTER_COMPANY_ID) == null ? null : (Company) companyService.get(((Long) computer.get(ConstantBD.COMPUTER_COMPANY_ID)).intValue()).orElse(null)).build());
		}
	
		return computerList;
	}
	
	public Optional<Computer> get(Integer id) {
		Map<String, Object> result = jdbcTemplate.queryForMap(GET_BY_ID, id);
		
		return Optional.of(new ComputerBuilder((String) result.get(ConstantBD.COMPANY_NAME))
				.withId((Long) result.get(ConstantBD.COMPUTER_ID))
				.withIntroducedDate((Date) result.get(ConstantBD.COMPUTER_INTRODUCED))
				.withDiscountedDate((Date) result.get(ConstantBD.COMPUTER_DISCONTINUED))
				.withCompany(result.get(ConstantBD.COMPUTER_COMPANY_ID) == null ? null : (Company) companyService.get(((Long) result.get(ConstantBD.COMPUTER_COMPANY_ID)).intValue()).orElse(null)).build());
	}

	public void add(Computer comp) {
		jdbcTemplate.update(ADD, 
				comp.getId(), 
				comp.getName(), 
				comp.getIntroducedDate(), 
				comp.getDiscontinuedDate(), 
				comp.getCompany() != null ? comp.getCompany().getId() : 0);
	}

	public void update(Computer comp) {
		jdbcTemplate.update(UPDATE, 
				comp.getName(), 
				comp.getIntroducedDate(), 
				comp.getDiscontinuedDate(), 
				comp.getCompany() != null ? comp.getCompany().getId() : 0,
				comp.getId());
	}

	public void delete(Computer comp) {
		jdbcTemplate.update(DELETE, comp.getId());
	}

}

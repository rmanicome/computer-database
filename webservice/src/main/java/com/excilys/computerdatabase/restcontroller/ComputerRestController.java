package com.excilys.computerdatabase.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerValidator;
import com.excilys.computerdatabase.validator.IncorrectInputException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/v1.0.0/", produces = "application/json")
public class ComputerRestController {
    
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerRestController.class);
    private static final String IDS_DO_NOT_MATCH = "request id and object id do not match";
    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ComputerValidator computerValidator;

    @GetMapping("computers/detail/{pk:\\d+}")
    public ResponseEntity<Computer> getComputer(@PathVariable("pk") Long pk) {
        Optional<Computer> result = computerService.get(pk);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("companies/detail/{pk:\\d+}")
    public ResponseEntity<Company> getCompany(@PathVariable("pk") Long pk) {
        Optional<Company> result = companyService.get(pk);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "computers")
    public List<Computer> listComputers() {
    	ArrayList<Computer> computerList = computerService.get();
		
		return computerList;
    }

    @GetMapping(path = "companies")
    public List<Company> listCompanies () {
    	ArrayList<Company> companyList = companyService.get();
		
		return companyList;
    }

    @PostMapping(path="computers", consumes = "application/json")
    public ResponseEntity<String> addComputer(@RequestBody Computer computer) {
    	try {
			computerValidator.checkComputer(
					computer.getName(), 
					computer.getIntroducedDate() == null ? "" : computer.getIntroducedDate().toString(), 
					computer.getDiscontinuedDate() == null ? "" : computer.getDiscontinuedDate().toString(), 
					computer.getCompany() == null ? null : computer.getCompany().getId().toString());
	        computerService.add(computer);
	        
	        return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (IncorrectInputException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @PutMapping(path = "computers/{pk:\\d+}", consumes = "application/json")
    public ResponseEntity<String> updateComputer(
            @PathVariable("pk") Long pk,
            @RequestBody Computer computer) {
        if(!pk.equals(computer.getId()))
            return new ResponseEntity<>(IDS_DO_NOT_MATCH, HttpStatus.BAD_REQUEST);
        try {
			computerValidator.checkComputer(
					computer.getName(), 
					computer.getIntroducedDate() == null ? "" : computer.getIntroducedDate().toString(), 
					computer.getDiscontinuedDate() == null ? "" : computer.getDiscontinuedDate().toString(), 
					computer.getCompany() == null ? null : computer.getCompany().getId().toString());
			computerService.update(computer);
        
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IncorrectInputException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @DeleteMapping("computers/{pk:\\d+}")
    public ResponseEntity<String> deleteComputer(@PathVariable("pk") Long pk) {
        computerService.delete(computerService.get(pk).get());
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("comapnies/{pk:\\d+}")
    public ResponseEntity<String> deleteCompany(@PathVariable("pk") Long pk) {
        companyService.delete(companyService.get(pk).get());
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.excilys.computerdatabase.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.excilys.computerdatabase.paginator.Page;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;

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
    
    @GetMapping("companies/detail/{name}")
    public ResponseEntity<Company> getCompany(@PathVariable("name") String name) {
        Optional<Company> result = companyService.get(name);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(path = {
            "computers",
            "computers/{page:\\d+}",
            "computers/{page:\\d+}/{resultsPerPage:\\d+}",
            "computers/search/{search}",
            "computers/search/{search}/{page:\\d+}",
            "computers/search/{search}/{page:\\d+}/{resultsPerPage:\\d+}"
            })
    public List<Computer> listComputers(
            @PathVariable(name = "page", required = false) Optional<Integer> page,
            @PathVariable(name = "resultsPerPage", required = false) Optional<Integer> resultsPerPage,
            @PathVariable(name = "search", required = false) Optional<String> search) {
    	Integer pageValue = page.orElse(0);
    	ArrayList<Computer> computerList = computerService.get();
		Page<Computer> paginator = new Page<>();
		
		if(search.isPresent()){
			ArrayList<Computer> searchList = new ArrayList<Computer>();
			for (Computer computer : computerList) {
				if(computer.getName().toLowerCase().startsWith(search.get().toLowerCase()) || (computer.getCompany() != null && computer.getCompany().getName().toLowerCase().startsWith(search.get().toLowerCase())))
					searchList.add(computer);
			}
			computerList = searchList;
		}
		if(computerList.size() / paginator.getMaxComputerPerPage() == 0)
			pageValue = 0;
		else if(pageValue > computerList.size() / paginator.getMaxComputerPerPage() + (computerList.size() % paginator.getMaxComputerPerPage() == 0 ? 0 : 1))
			pageValue = computerList.size() / paginator.getMaxComputerPerPage() + (computerList.size() % paginator.getMaxComputerPerPage() == 0 ? 0 : 1);
			
		if(resultsPerPage.isPresent())
			Page.setMaxComputerPerPage(resultsPerPage.get());
		
		Page.setPageNumber(pageValue);
		
		return paginator.get(computerList);
    }
    
    @GetMapping(path = "companies")
    public List<Company> listCompanies () {
    	ArrayList<Company> companyList = companyService.get();
		
		return companyList;
    }
    
    @PostMapping(path="computers", consumes = "application/json")
    public ResponseEntity<String> addComputer(@RequestBody Computer computer) {
        computerService.add(computer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping(path = "computers/{pk:\\d+}", consumes = "application/json")
    public ResponseEntity<String> updateComputer(
            @PathVariable("pk") Long pk,
            @RequestBody Computer computer) {
        if(!pk.equals(computer.getId()))
            return new ResponseEntity<>(IDS_DO_NOT_MATCH, HttpStatus.BAD_REQUEST);
        computerService.update(computer);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
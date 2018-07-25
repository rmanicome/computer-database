package com.excilys.computerdatabase.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerValidator;
import com.excilys.computerdatabase.validator.IncorrectInputException;

@Controller
@RequestMapping(ControllerConstant.HOME)
public class EditComputerController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerValidator computerValidator;
	@Autowired
	private ComputerMapper computerMapper;
	
	@GetMapping(ControllerConstant.EDIT_COMPUTER)
	public String showEditPage(ModelMap model, @RequestParam(name="computer", required=true) String computer) {
		ArrayList<Computer> computerList = computerService.get();
		Integer computerId = Integer.parseInt(computer);
		
		if(computerId > computerList.size()) {
			model.addAttribute("error", "This id is not in the database");
			return ControllerConstant.ERROR_500;
		}
		
		model.addAttribute("computer", computerService.get(computerId).get());
		model.addAttribute("companies", companyService.get());
		
		return ControllerConstant.EDIT_COMPUTER;
	}
	
	@PostMapping(ControllerConstant.EDIT_COMPUTER)
	public String editComputer(ModelMap model, @RequestParam(name="id", required=true) String id, @RequestParam(name="computerName", required=true) String name, @RequestParam(name="introduced", required=false) String introduced, @RequestParam(name="discontinued", required=false) String discontinued, @RequestParam(name="companyId", required=false) String company) {
		try {
			computerValidator.checkComputer(name, introduced, discontinued, company);
			computerService.update(computerMapper.buildComputer(id, name, introduced, discontinued, company));
			
			model.addAttribute("computer", computerService.get(Integer.parseInt(id)).get());
			model.addAttribute("companies", companyService.get());
			model.addAttribute("done", true);
		} catch (IncorrectInputException e) {
			model.addAttribute("error", e.getMessage());
			
			return ControllerConstant.ERROR_500;
		}
		
		return ControllerConstant.EDIT_COMPUTER;
	}
}

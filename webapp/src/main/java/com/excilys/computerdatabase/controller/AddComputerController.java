package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerValidator;
import com.excilys.computerdatabase.validator.IncorrectInputException;

@Controller
@RequestMapping(ControllerConstant.HOME)
public class AddComputerController {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerValidator computerValidator;
	@Autowired
	private ComputerMapper computerMapper;
	
	@GetMapping(ControllerConstant.ADD_COMPUTER)
	public String addComputer(ModelMap model) {
		model.addAttribute(ControllerConstant.COMPANIE_RESOURCES, companyService.get());
		
		return ControllerConstant.ADD_COMPUTER;
	}
	
	@PostMapping(ControllerConstant.ADD_COMPUTER)
	public ModelAndView addComputer(ModelMap model, @RequestParam(name="computerName", required=true) String name, @RequestParam(name="introduced", required=false) String introduced, @RequestParam(name="discontinued", required=false) String discontinued, @RequestParam(name="companyId", required=false) String company) {
		try {
			computerValidator.checkComputer(name, introduced, discontinued, company);
			computerService.add(computerMapper.buildComputer("0", name, introduced, discontinued, company));
		} catch (IncorrectInputException e) {
			model.addAttribute("error", e.getMessage());
			
			return new ModelAndView("redirect:"+ControllerConstant.HOME+ControllerConstant.ERROR_500, model);
		}
		
		return new ModelAndView("redirect:"+ControllerConstant.HOME+ControllerConstant.DASHBOARD);
	}
}

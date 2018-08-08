package com.excilys.computerdatabase.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.paginator.Page;
import com.excilys.computerdatabase.service.ComputerService;

@Controller
@RequestMapping(ControllerConstant.HOME)
public class DashboardController {
	@Autowired
	private ComputerService computerService;
	
	@GetMapping(ControllerConstant.DASHBOARD)
	public String showComputers(ModelMap model, @RequestParam(name="page", required=false, defaultValue="1") String pageString, @RequestParam(name="search", required=false) String search) {
		ArrayList<Computer> computerList = computerService.get();
		Integer page = Integer.parseInt(pageString);
		Page<Computer> paginator = new Page<>();
		
		if(page > computerList.size() / paginator.getMaxComputerPerPage() + (computerList.size() % paginator.getMaxComputerPerPage() == 0 ? 0 : 1))
			page = computerList.size() / paginator.getMaxComputerPerPage() + (computerList.size() % paginator.getMaxComputerPerPage() == 0 ? 0 : 1);
			
		if(search != null){
			ArrayList<Computer> searchList = new ArrayList<Computer>();
			for (Computer computer : computerList) {
				if(computer.getName().toLowerCase().startsWith(search.toLowerCase()) || (computer.getCompany() != null && computer.getCompany().getName().toLowerCase().startsWith(search.toLowerCase())))
					searchList.add(computer);
			}
			computerList = searchList;
		}
		
		Page.setPageNumber(page-1);
		
		model.addAttribute("page", page);
		model.addAttribute("computerPerPage", paginator.getMaxComputerPerPage());
		model.addAttribute("computers", paginator.get(computerList));
		model.addAttribute("size", computerList.size());
		model.addAttribute("pageMax", computerList.size() / paginator.getMaxComputerPerPage() + (computerList.size() % paginator.getMaxComputerPerPage() == 0 ? 0 : 1));
		
		return ControllerConstant.DASHBOARD;
	}
	
	@PostMapping(ControllerConstant.DASHBOARD)
	public String showComputers(ModelMap model, @RequestParam(name="clicked", required=false, defaultValue="50") String clicked) {
		ArrayList<Computer> computerList = computerService.get();
		Page<Computer> page = new Page<Computer>();
		Integer clickedValue = Integer.parseInt(clicked);
		
		Page.setMaxComputerPerPage(clickedValue);
		
		model.addAttribute("computers", page.get(computerList));
		model.addAttribute("size", computerList.size());
		model.addAttribute("pageMax", computerList.size() / page.getMaxComputerPerPage() + (computerList.size() % page.getMaxComputerPerPage() == 0 ? 0 : 1));
		
		return ControllerConstant.DASHBOARD;
	}
}

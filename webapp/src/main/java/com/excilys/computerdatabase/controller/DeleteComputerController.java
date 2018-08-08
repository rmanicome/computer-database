package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.computerdatabase.service.ComputerService;

@Controller
@RequestMapping(ControllerConstant.HOME)
public class DeleteComputerController {
	@Autowired
	private ComputerService computerService;
	
	@PostMapping(ControllerConstant.DELETE_COMPUTER)
	public RedirectView deleteComputer(ModelMap model, @RequestParam(name="selection", required=true) String selection) {
		int t = 0;
		
		while(selection.indexOf(",", t) >= 0){
			computerService.delete(computerService.get(Long.parseLong(selection.substring(t, selection.indexOf(",", t)))).get());
			t = selection.indexOf(",", t)+1;
		}
		computerService.delete(computerService.get(Long.parseLong(selection.substring(selection.lastIndexOf(',')+1, selection.length()))).get());
		
		return new RedirectView(ControllerConstant.DASHBOARD);
	}
}

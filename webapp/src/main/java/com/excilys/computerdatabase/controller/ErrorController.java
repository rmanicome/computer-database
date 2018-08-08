package com.excilys.computerdatabase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ControllerConstant.HOME)
public class ErrorController {
	
	@GetMapping(value = {ControllerConstant.ERROR_403, ControllerConstant.ERROR_404, ControllerConstant.ERROR_500})
	public String showError(HttpServletRequest request) {
		return request.getServletPath();
	}
}

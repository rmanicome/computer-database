package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ControllerConstant.HOME)
public class LoginController {
	@Autowired
	AuthenticationProvider authentication;
	
	@GetMapping(ControllerConstant.LOGIN)
	public String showLoginPage(ModelMap model) {
		return ControllerConstant.LOGIN;
	}
}

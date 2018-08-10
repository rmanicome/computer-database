package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.service.UserService;
import com.excilys.computerdatabase.springconfig.AuthenticationProvider;
import com.excilys.computerdatabase.validator.IncorrectInputException;
import com.excilys.computerdatabase.validator.UserValidator;

@Controller
@RequestMapping(ControllerConstant.HOME)
public class LoginController {
	@Autowired
	private UserValidator userValidator;
	AuthenticationProvider authentication = new AuthenticationProvider();
	
	@GetMapping(ControllerConstant.LOGIN)
	public String showLoginPage(ModelMap model) {
		return ControllerConstant.LOGIN;
	}
	
//	@PostMapping(ControllerConstant.LOGIN)
//	public ModelAndView login(ModelMap model, @RequestParam(name="userName", required=true) String name, @RequestParam(name="password", required=true) String password) {
//		try {
//			userValidator.checkUserInput(name, password);
//			
//			if(!password.equals(user.getPassword())) {
//				model.addAttribute("error", "Wrong user name or password");
//			
//				return new ModelAndView("redirect:"+ControllerConstant.HOME+ControllerConstant.LOGIN, model);
//			}
//		} catch (IncorrectInputException e) {
//			model.addAttribute("error", e.getMessage());
//			
//			return new ModelAndView("redirect:"+ControllerConstant.HOME+ControllerConstant.ERROR_500, model);
//		}
//		
//		return new ModelAndView("redirect:"+ControllerConstant.HOME+ControllerConstant.DASHBOARD);
//	}
}

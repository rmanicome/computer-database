package com.excilys.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerValidator;
import com.excilys.computerdatabase.validator.IncorrectInputException;

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerValidator computerValidator;
	@Autowired
	private ComputerMapper computerMapper;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("companies", companyService.get());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/addComputer.jsp" ).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			computerValidator.checkComputer(request.getParameter("computerName"), request.getParameter("introduced"), request.getParameter("discontinued"), request.getParameter("companyId"));
			computerService.add(computerMapper.nom("0", request.getParameter("computerName"), request.getParameter("introduced"), request.getParameter("discontinued"), request.getParameter("companyId")));
			response.sendRedirect("dashboard");
		} catch (IncorrectInputException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		}
	}
}
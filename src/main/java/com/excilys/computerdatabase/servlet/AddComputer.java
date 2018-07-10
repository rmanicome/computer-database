package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerValidator;

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CompanyService companyService = CompanyService.getInstance();
	private static ComputerService computerService = ComputerService.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("companies", companyService.get());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/addComputer.jsp" ).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ComputerValidator.getInstance().checkComputer(request.getParameter("computerName"), request.getParameter("introduced"), request.getParameter("discontinued"), request.getParameter("companyId"))){
			computerService.add(ComputerMapper.getInstance().nom("0", request.getParameter("computerName"), request.getParameter("introduced"), request.getParameter("discontinued"), request.getParameter("companyId")));
			response.sendRedirect("dashboard");
		} else {
			request.setAttribute("error", "The computer was not added. One or many fields were wrong.");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		}
	}
}
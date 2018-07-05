package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("companies", CompanyService.getInstance().get());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/addComputer.jsp" ).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Company company = request.getParameter("companyId") == null ? null : (CompanyService.getInstance().get(Integer.parseInt(request.getParameter("companyId"))).orElse(null));
		ComputerService compList = ComputerService.getInstance();
		Computer newComp = new Computer(request.getParameter("computerName"));
		newComp.setIntroducedDate(request.getParameter("introduced") != null && request.getParameter("introduced").matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]") ? Date.valueOf((String)request.getParameter("introduced")) : null);
		newComp.setDiscontinuedDate(request.getParameter("discontinued") != null && request.getParameter("discontinued").matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]") ? Date.valueOf((String)request.getParameter("discontinued")) : null); 
		newComp.setCompany(company);
		compList.add(newComp);
		response.sendRedirect("dashboard");
	}
}
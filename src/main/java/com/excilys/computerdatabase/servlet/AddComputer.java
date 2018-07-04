package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.SQLCompanyQuery;
import com.excilys.computerdatabase.service.CompanyValues;
import com.excilys.computerdatabase.service.ComputerValues;

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("companies", new CompanyValues().getCompanyList());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/addComputer.jsp" ).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Company company = request.getParameter("companyId") == null ? null : SQLCompanyQuery.getCompany(Integer.parseInt(request.getParameter("companyId")));
			ComputerValues compList = new ComputerValues();
			Computer newComp = new Computer(request.getParameter("computerName"));
			newComp.setIntroducedDate(request.getParameter("introduced") != null && request.getParameter("introduced").matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]") ? Date.valueOf((String)request.getParameter("introduced")) : null);
			newComp.setDiscountedDate(request.getParameter("discounted") != null && request.getParameter("discounted").matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]") ? Date.valueOf((String)request.getParameter("discounted")) : null); 
			newComp.setCompany(company);
			compList.addComputer(newComp);
			response.sendRedirect("dashboard");
		} catch (SQLException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		}
	}
}
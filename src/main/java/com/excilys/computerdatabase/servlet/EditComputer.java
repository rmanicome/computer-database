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
import com.excilys.computerdatabase.persistence.SQLComputerQuery;
import com.excilys.computerdatabase.service.CompanyValues;
import com.excilys.computerdatabase.service.ComputerValues;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Integer.parseInt(request.getParameter("computer")) > new ComputerValues().getComputerList().size()){
			request.setAttribute("error", "This id is not in the database");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		} else {
			try {
				request.setAttribute("computer", SQLComputerQuery.getComputer(Integer.parseInt(request.getParameter("computer"))));
				request.setAttribute("companies", new CompanyValues().getCompanyList());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/views/editComputer.jsp" ).forward( request, response );
			} catch (NumberFormatException e) {
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
			} catch (SQLException e) {
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getContentLength() != 0){
				Company company = request.getParameter("companyId") == null ? null : SQLCompanyQuery.getCompany(Integer.parseInt(request.getParameter("companyId")));
				Computer newComp = new Computer(
						Integer.parseInt(request.getParameter("id")),
						request.getParameter("computerName"),
						request.getParameter("introduced").matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]") ? Date.valueOf((String)request.getParameter("introduced")) : null,
						request.getParameter("discounted").matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]") ? Date.valueOf((String)request.getParameter("discounted")) : null, 
						company);
				SQLComputerQuery.updateComputer(newComp);
				request.setAttribute("computer", SQLComputerQuery.getComputer(Integer.parseInt(request.getParameter("computer"))));
				request.setAttribute("companies", new CompanyValues().getCompanyList());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/views/editComputer.jsp" ).forward( request, response );
			}
		} catch (SQLException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		}
	}
}

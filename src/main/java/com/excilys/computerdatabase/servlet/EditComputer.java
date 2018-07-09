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

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Integer.parseInt(request.getParameter("computer")) > ComputerService.getInstance().get().size()){
			request.setAttribute("error", "This id is not in the database");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		} else {
			try {
				request.setAttribute("computer", ComputerService.getInstance().get(Integer.parseInt(request.getParameter("computer"))).get());
				request.setAttribute("companies", CompanyService.getInstance().get());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/views/editComputer.jsp" ).forward( request, response );
			} catch (NumberFormatException e) {
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ComputerValidator.getInstance().checkComputer(request.getParameter("computerName"), request.getParameter("introduced"), request.getParameter("discontinued"), request.getParameter("companyId"))){
			ComputerService.getInstance().update(ComputerMapper.getInstance().nom(request.getParameter("id"), request.getParameter("computerName"), request.getParameter("introduced"), request.getParameter("discontinued"), request.getParameter("companyId")));
			request.setAttribute("computer", ComputerService.getInstance().get(Integer.parseInt(request.getParameter("computer"))).get());
			request.setAttribute("companies", CompanyService.getInstance().get());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/editComputer.jsp" ).forward( request, response );
		} else {
			request.setAttribute("error", "The computer was not updated. One or many fields were wrong.");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		}
	}
}

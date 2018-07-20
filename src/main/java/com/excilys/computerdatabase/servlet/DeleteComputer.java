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

import com.excilys.computerdatabase.service.ComputerService;

@WebServlet("/deleteComputer")
public class DeleteComputer extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ids = request.getParameter("selection");
			int t = 0;
			
			while(ids.indexOf(",", t) >= 0){
				computerService.delete(computerService.get(Integer.parseInt(ids.substring(t, ids.indexOf(",", t)))).get());
				t = ids.indexOf(",", t)+1;
			}
			computerService.delete(computerService.get(Integer.parseInt(ids.substring(ids.lastIndexOf(',')+1, ids.length()))).get());
			response.sendRedirect("dashboard");
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Id of the computer is not recognized");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		}
	}
}

package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.persistence.SQLComputerQuery;

@WebServlet("/deleteComputer")
public class DeleteComputer extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ids = request.getParameter("selection");
			int t = 0;
			
			while(ids.indexOf(",", t) >= 0){
				SQLComputerQuery.deleteComputer(SQLComputerQuery.getComputer(Integer.parseInt(ids.substring(t, ids.indexOf(",", t)))));
				t = ids.indexOf(",", t)+1;
			}
			SQLComputerQuery.deleteComputer(SQLComputerQuery.getComputer(Integer.parseInt(ids.substring(ids.lastIndexOf(',')+1, ids.length()))));
			response.sendRedirect("dashboard");
		} catch (NumberFormatException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		} catch (SQLException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/views/500.jsp" ).forward( request, response );
		}
	}
}

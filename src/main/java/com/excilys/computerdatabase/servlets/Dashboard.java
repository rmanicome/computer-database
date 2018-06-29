package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Page;
import com.excilys.computerdatabase.service.ComputerValues;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerValues computerValues = new ComputerValues();
		ArrayList<Computer> computerList = computerValues.getComputerList();
		Page<Computer> page = new Page<Computer>();
		if(request.getParameter("page")!=null){
			switch(request.getParameter("page")){
			case "p" :
				if(page.hasPreviousPage())
					page.getPreviousPage();
				break;
			case "n" :
				if(page.hasNextPage(computerValues))
					page.getNextPage();
				break;
			default :
				Integer p = Integer.parseInt(request.getParameter("page"));
				Page.setPageNumber(p-1);
				break;
			}
		} else {
			Page.setPageNumber(0);
		}
		request.setAttribute( "computers", page.getPage(computerList));
		request.setAttribute("size", computerList.size());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
	}
}

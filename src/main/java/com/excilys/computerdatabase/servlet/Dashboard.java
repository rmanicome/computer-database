package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.Page;
import com.excilys.computerdatabase.service.ComputerService;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService computerService = ComputerService.getInstance();
		ArrayList<Computer> computerList = computerService.get();
		Page<Computer> page = new Page<Computer>();
		Integer p;
		
		if(request.getParameter("search") != null){
			ArrayList<Computer> searchList = new ArrayList<Computer>();
			request.setAttribute("search", request.getParameter("search"));
			for (Computer computer : computerList) {
				if(computer.getName().toLowerCase().contains(request.getParameter("search").toLowerCase()))
					searchList.add(computer);
			}
			computerList = searchList;
		}
		
		if(request.getParameter("page")!=null){
			if(Integer.parseInt(request.getParameter("page")) < computerList.size() / Page.getMaxComputerPerPage() + (computerList.size() % Page.getMaxComputerPerPage() == 0 ? 0 : 1))
				p = Integer.parseInt(request.getParameter("page"));
			else
				p = computerList.size() / Page.getMaxComputerPerPage() + (computerList.size() % Page.getMaxComputerPerPage() == 0 ? 0 : 1);
			Page.setPageNumber(p-1);
		} else {
			p = 1;
			Page.setPageNumber(0);
		}
				
		request.setAttribute("page", p);
		request.setAttribute("computerPerPage", Page.getMaxComputerPerPage());
		request.setAttribute("computers", page.get(computerList));
		request.setAttribute("size", computerList.size());
		request.setAttribute("pageMax", computerList.size() / Page.getMaxComputerPerPage() + (computerList.size() % Page.getMaxComputerPerPage() == 0 ? 0 : 1));
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService computerService = ComputerService.getInstance();
		ArrayList<Computer> computerList = computerService.get();
		Page<Computer> page = new Page<Computer>();
		
		if(request.getParameter("clicked") != null){
			Page.setMaxComputerPerPage(Integer.parseInt(request.getParameter("clicked")));
		}
		
		request.setAttribute("computers", page.get(computerList));
		request.setAttribute("size", computerList.size());
		request.setAttribute("pageMax", computerList.size() / Page.getMaxComputerPerPage() + (computerList.size() % Page.getMaxComputerPerPage() == 0 ? 0 : 1));
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
	}
}

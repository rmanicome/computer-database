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
	private static ComputerService computerService = ComputerService.getInstance();
	
	private Integer pageRequest(HttpServletRequest request) {
		ArrayList<Computer> computerList = computerService.get();
		Integer page;
		
		if(request.getParameter("page")!=null){
			if(Integer.parseInt(request.getParameter("page")) < computerList.size() / Page.getMaxComputerPerPage() + (computerList.size() % Page.getMaxComputerPerPage() == 0 ? 0 : 1))
				page = Integer.parseInt(request.getParameter("page"));
			else
				page = computerList.size() / Page.getMaxComputerPerPage() + (computerList.size() % Page.getMaxComputerPerPage() == 0 ? 0 : 1);
			Page.setPageNumber(page-1);
		} else {
			page = 1;
			Page.setPageNumber(page-1);
		}
		
		return page;
	}
	
	private ArrayList<Computer> searchRequest(String request) {
		ArrayList<Computer> searchList = new ArrayList<Computer>();
		
		for (Computer computer : computerService.get()) {
			if(computer.getName().toLowerCase().startsWith(request.toLowerCase()) || (computer.getCompany() != null && computer.getCompany().getName().toLowerCase().startsWith(request.toLowerCase())))
				searchList.add(computer);
		}
		
		return searchList;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Computer> computerList = computerService.get();
		Page<Computer> page = new Page<Computer>();
			
		if(request.getParameter("search") != null){
			request.setAttribute("search", request.getParameter("search"));
			computerList = searchRequest(request.getParameter("search"));
		}
		
		request.setAttribute("page", pageRequest(request));
		request.setAttribute("computerPerPage", Page.getMaxComputerPerPage());
		request.setAttribute("computers", page.get(computerList));
		request.setAttribute("size", computerList.size());
		request.setAttribute("pageMax", computerList.size() / Page.getMaxComputerPerPage() + (computerList.size() % Page.getMaxComputerPerPage() == 0 ? 0 : 1));
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

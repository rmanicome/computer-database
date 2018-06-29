package com.excilys.computerdatabase.model;

import java.util.ArrayList;

import com.excilys.computerdatabase.service.ComputerValues;

public class Page<T> {
	private static Integer maxComputerPerPage = 10;
	private static Integer pageNumber = 0;
	
	public static void setMaxComputerPerPage(Integer newMax) {
		maxComputerPerPage = newMax;
	}
	
	public static void setPageNumber(Integer newPage) {
		pageNumber = newPage;
	}
	
	public void getPreviousPage() {
		pageNumber -= 1;
	}

	public void getNextPage() {
		pageNumber += 1;
	}

	public ArrayList<T> getPage(ArrayList<T> values) {
		if((pageNumber+1) * maxComputerPerPage < (values.size()))
			return new ArrayList<T> (values.subList(pageNumber * maxComputerPerPage , (pageNumber+1) * maxComputerPerPage));

		return new ArrayList<T> (values.subList(pageNumber * maxComputerPerPage , values.size()));
	}
	
	public Boolean hasPreviousPage() {
		return pageNumber > 0;
	}

	public Boolean hasNextPage(ComputerValues computerValues) {
		return pageNumber * maxComputerPerPage < computerValues.getComputerList().size();
	}
}
package com.excilys.computerdatabase.model;

import java.util.ArrayList;

import com.excilys.computerdatabase.service.ComputerService;

public class Page<T> {
	private static Integer maxComputerPerPage = 50;
	private static Integer pageNumber = 0;

	public static int getMaxComputerPerPage() {
		return maxComputerPerPage;
	}
	
	public static void setMaxComputerPerPage(Integer newMax) {
		maxComputerPerPage = newMax;
	}
	
	public static void setPageNumber(Integer newPage) {
		pageNumber = newPage;
	}
	
	public void previousPage() {
		pageNumber -= 1;
	}

	public void nextPage() {
		pageNumber += 1;
	}

	public ArrayList<T> get(ArrayList<T> values) {
		if((pageNumber+1) * maxComputerPerPage < (values.size()))
			return new ArrayList<T> (values.subList(pageNumber * maxComputerPerPage , (pageNumber+1) * maxComputerPerPage));

		return new ArrayList<T> (values.subList(pageNumber * maxComputerPerPage , values.size()));
	}
	
	public Boolean hasPreviousPage() {
		return pageNumber > 0;
	}

	public Boolean hasNextPage(ComputerService computerService) {
		return pageNumber * maxComputerPerPage < computerService.get().size();
	}
}
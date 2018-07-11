package com.excilys.computerdatabase.validator;

public class IncorrectInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectInputException() {
		super();
	}
	
	public IncorrectInputException(String s) {
		super(s);
	}
}

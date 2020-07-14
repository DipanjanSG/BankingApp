package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while Generating Bank Statement
 */
public class BankStatementException extends Exception{

	private final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(BankStatementException.class);

	
	public BankStatementException(String ex) {
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "BankStatementException [exception=" + exceptionMessage + "]";
	}
	
}

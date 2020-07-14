package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while accessing customer DB
 */
public class CustomerDBAccessException extends Exception{

	private final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(CustomerDBAccessException.class);

	
	public CustomerDBAccessException(String ex) {
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "CustomerDBAccessException [exception=" + exceptionMessage + "]";
	}
	
}

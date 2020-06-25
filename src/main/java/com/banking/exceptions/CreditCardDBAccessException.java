package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while accessing Credit card DB
 */
public class CreditCardDBAccessException extends Exception{

	private final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(CreditCardDBAccessException.class);

	
	public CreditCardDBAccessException(String ex) { 
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "CreditCardDBAccessException [exception=" + exceptionMessage + "]";
	}
}

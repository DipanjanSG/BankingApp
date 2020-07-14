package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while accessing Transaction DB
 */
public class TransactionDBAccessException extends Exception{

	private final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(TransactionDBAccessException.class);

	
	public TransactionDBAccessException(String ex) {
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "TransactionDBAccessException [exception=" + exceptionMessage + "]";
	}
	
}

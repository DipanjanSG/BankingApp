package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while accessing Credentials DB
 */
public class CredentialsDBAccessException extends Exception{

	private final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(CredentialsDBAccessException.class);

	
	public CredentialsDBAccessException(String ex) { 
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "CredentialsDBAccessException [exception=" + exceptionMessage + "]";
	}
}

package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while Account Creation
 */
public class AccountCreationException extends Exception{
	
	private final String exceptionMessage;
	private static final  Logger LOGGER = Logger.getLogger(AccountCreationException.class);

	
	public AccountCreationException(String ex) {
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	
	@Override
	public String toString() {
		return "AccountCreationException [exception=" + exceptionMessage + "]";
	}
	
	
}

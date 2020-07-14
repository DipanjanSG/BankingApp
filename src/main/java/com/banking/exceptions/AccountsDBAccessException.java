package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while Accounts Table Access
 */
public class AccountsDBAccessException extends Exception{

	private final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(AccountsDBAccessException.class);

	
	public AccountsDBAccessException(String ex) { 
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "AccountsDBAccessException [exception=" + exceptionMessage + "]";
	}
}

package com.banking.exceptions;

import org.apache.log4j.Logger;

public class AccountCreationException extends Exception{
	
	final String exceptionMessage;
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

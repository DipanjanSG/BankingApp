package com.banking.exceptions;

import org.apache.log4j.Logger;

public class AccountCreationException extends Exception{
	
	private String exception;
	private final static Logger LOGGER = Logger.getLogger(AccountCreationException.class);

	
	public AccountCreationException(String ex) {
		this.exception = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "AccountCreationException [exception=" + exception + "]";
	}
	
	
}

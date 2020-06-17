package com.banking.exceptions;

import org.apache.log4j.Logger;

public class BankStatementException extends Exception{

	final String exceptionMessage;
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

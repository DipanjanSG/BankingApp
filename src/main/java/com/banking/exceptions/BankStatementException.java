package com.banking.exceptions;

import org.apache.log4j.Logger;

public class BankStatementException extends Exception{

	private String exception;
	private final static Logger LOGGER = Logger.getLogger(BankStatementException.class);

	
	public BankStatementException(String ex) {
		this.exception = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "BankStatementException [exception=" + exception + "]";
	}
	
}

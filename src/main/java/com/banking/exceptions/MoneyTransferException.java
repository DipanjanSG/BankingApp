package com.banking.exceptions;

import org.apache.log4j.Logger;

public class MoneyTransferException extends Exception{

	private String exception;
	final static Logger LOGGER = Logger.getLogger(MoneyTransferException.class);

	
	public MoneyTransferException(String ex) { 
		this.exception = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "MoneyTransferException [exception=" + exception + "]";
	}
}

package com.banking.exceptions;

import org.apache.log4j.Logger;

public class MoneyTransferException extends Exception{

	final String exceptionMessage;
	static final Logger LOGGER = Logger.getLogger(MoneyTransferException.class);

	
	public MoneyTransferException(String ex) { 
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "MoneyTransferException [exception=" + exceptionMessage + "]";
	}
}

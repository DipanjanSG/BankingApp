package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while transferring money between accounts
 *  */
public class MoneyTransferException extends Exception{

	private final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(MoneyTransferException.class);

	
	public MoneyTransferException(String ex) { 
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "MoneyTransferException [exception=" + exceptionMessage + "]";
	}
}

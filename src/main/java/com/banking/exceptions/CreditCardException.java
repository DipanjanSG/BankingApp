package com.banking.exceptions;

import org.apache.log4j.Logger;

public class CreditCardException  extends Exception{


	final String exceptionMessage;
	private static final Logger LOGGER = Logger.getLogger(CreditCardException.class);

	
	public CreditCardException(String ex) {
		this.exceptionMessage = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "CreditCardException [exception=" + exceptionMessage + "]";
	}
	
}

package com.banking.exceptions;

import org.apache.log4j.Logger;

public class CreditCardException  extends Exception{


	private String exception;
	final static Logger LOGGER = Logger.getLogger(CreditCardException.class);

	
	public CreditCardException(String ex) {
		this.exception = ex;
		LOGGER.fatal(ex);
	}
	@Override
	public String toString() {
		return "CreditCardException [exception=" + exception + "]";
	}
	
}

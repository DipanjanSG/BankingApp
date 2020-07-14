package com.banking.exceptions;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Custom Exception to be thrown while making Credit card transactions
 */
public class CreditCardException  extends Exception{


	private final String exceptionMessage;
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

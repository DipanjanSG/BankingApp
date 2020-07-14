package com.banking.logging;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Logs for Credit card transactions
 */
public class AuthorizeCCTransactionsLogs {

	private static final Logger LOGGER = Logger.getLogger(AuthorizeCCTransactionsLogs.class);
	
	public void beforeApprovingCCTransactions() {
		LOGGER.info("Going to begin transaction for Credit Card");
	}
	
	public void afterApprovingCCTransactions() {
		LOGGER.info("Going to end transaction for Credit Card");
	}
}

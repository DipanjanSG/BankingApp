package com.banking.logging;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Logs for money Transactions 
 */
public class TransactionDaoLogs {

	private static final Logger LOGGER = Logger.getLogger(TransactionDaoLogs.class);
	
	public void beforePerformTransaction() {
		LOGGER.info("Starting Amount Transfer");
	}
	
	public void afterPerformTransaction() {
		LOGGER.info("Amount Transfer completed");
	}
}

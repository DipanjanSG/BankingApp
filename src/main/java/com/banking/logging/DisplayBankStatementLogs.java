package com.banking.logging;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Logs for money Transactions 
 */
public class DisplayBankStatementLogs {

	private static final Logger LOGGER = Logger.getLogger(DisplayBankStatementLogs.class);
	
	public void beforeFetchingTransactionDetails() {
		LOGGER.info("Going to fetch Transactions details ");
	}
	
	public void afterFetchingTransactionDetails() {
		LOGGER.info("Transaction details fetched");
	}
}

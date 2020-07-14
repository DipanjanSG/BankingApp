package com.banking.logging;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Logs for Account Creation
 */
public class CreateAccountLogs {

	private static final Logger LOGGER = Logger.getLogger(CreateAccountLogs.class);
	
	public void beforeCreateNewAccount() {
		LOGGER.info("Go into create a new account");
	}
	
	public void afterCreateNewAccount() {
		LOGGER.info("Have exited account Creation Utility");
	}
}

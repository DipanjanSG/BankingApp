package Logger;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta
 * @purpose - Logs for Account Creation
 */
public class CreateAccountLogs {

	final static Logger logger = Logger.getLogger(CreateAccountLogs.class);
	
	public void beforeCreateNewAccount() {
		logger.info("Go into create a new account");
	}
	
	public void afterCreateNewAccount() {
		logger.info("Have exited account Creation Utility");
	}
}

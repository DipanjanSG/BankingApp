package Logger;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta
 * @purpose - Logs for Credit card transactions
 */
public class AuthorizeCCTransactionsLogs {

	final static Logger logger = Logger.getLogger(AuthorizeCCTransactionsLogs.class);
	
	public void beforeApprovingCCTransactions() {
		logger.info("Going to begin transaction for Credit Card");
	}
	
	public void afterApprovingCCTransactions() {
		logger.info("Going to end transaction for Credit Card");
	}
}

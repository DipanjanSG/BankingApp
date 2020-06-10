package Logger;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta
 * @purpose - Logs for money Transactions 
 */
public class TransactionDaoLogs {

	final static Logger logger = Logger.getLogger(TransactionDaoLogs.class);
	
	public void beforePerformTransaction() {
		logger.info("Starting Amount Transfer");
	}
	
	public void afterPerformTransaction() {
		logger.info("Amount Transfer completed");
	}
}

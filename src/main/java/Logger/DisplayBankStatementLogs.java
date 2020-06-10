package Logger;

import org.apache.log4j.Logger;

/**
 * @author Dipanjan Sengupta
 * @purpose - Logs for money Transactions 
 */
public class DisplayBankStatementLogs {

	final static Logger logger = Logger.getLogger(DisplayBankStatementLogs.class);
	
	public void beforeFetchingTransactionDetails() {
		logger.info("Going to fetch Transactions details ");
	}
	
	public void afterFetchingTransactionDetails() {
		logger.info("Transaction details fetched");
	}
}

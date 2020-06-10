package Transactions;

import java.io.IOException;

import org.apache.log4j.Logger;

import CustomerActivities.DisplayBankStatement;
import TransactionDetails.Transaction;
import TransactionDetails.TransactionDaoImpl;
import configs.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Contains methods to interact with DB and upadate money transaction details
 */
public class TransactionsDao {
	final static Logger logger = Logger.getLogger(TransactionsDao.class);

	public boolean performTransaction(Accounts accountsBean, String transactionType, int customerId ) throws ClassNotFoundException, IOException {
	        AccountsDaoImpl accountsDaoImpl = ContextBeans.getAcountsDaoImpl();
	        AccountsDaoImpl accountsDaoImplLoggedInUser = ContextBeans.getAcountsDaoImpl();
	        
	        Accounts accountsBeanFromDatabase = accountsDaoImpl.get(accountsBean);
	        if (accountsBeanFromDatabase == null)
	        	return false;
	        Accounts loggedInUsersAccount =  accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerId);

	        Transaction transaction = ContextBeans.getTransactionDetailsBean();
	        
	        if ( transactionType.equals("credit") && loggedInUsersAccount.accountBalance >= accountsBean.accountBalance) {
	        	accountsBeanFromDatabase.incrementAccountBalance(accountsBean.accountBalance);
	        	loggedInUsersAccount.decrementAccountBalance(accountsBean.accountBalance);
	        	transaction.setFromAccount(loggedInUsersAccount.getAccountNumber());
		        transaction.setToAccount(accountsBean.accountNumber);
			} else if (transactionType.equals("debit") && accountsBeanFromDatabase.accountBalance >= accountsBean.accountBalance) {
				accountsBeanFromDatabase.decrementAccountBalance(accountsBean.accountBalance);
				loggedInUsersAccount.incrementAccountBalance(accountsBean.accountBalance);
				transaction.setFromAccount(accountsBean.accountNumber);
		        transaction.setToAccount(loggedInUsersAccount.getAccountNumber());
			}
			else {
				logger.error(transactionType + " transaction has FAILED on account number -" + loggedInUsersAccount.accountNumber + " to account number -" + accountsBean.accountNumber);
				return false;
			}
	        
	        accountsDaoImpl.update(accountsBeanFromDatabase);
	        accountsDaoImplLoggedInUser.update(loggedInUsersAccount);
	        
	        TransactionDaoImpl transactionDaoImpl = ContextBeans.getTransactionDaoImpl();
	        
		    transaction.setTransactionType(transactionType);
		    transaction.setAmount(accountsBean.accountBalance);
		    transaction.setFromAccountAmt(accountsBeanFromDatabase.getAccountBalance());
		    transaction.setToAccountAmt(loggedInUsersAccount.getAccountBalance());
		    if (transactionDaoImpl.save(transaction) >0 ) {
   			    logger.info(transactionType + " transaction has been successful on account number -" + loggedInUsersAccount.accountNumber + " to account number -" + accountsBean.accountNumber);
				return true;

		    }
			logger.error(transactionType + " transaction has FAILED on  account number -" + loggedInUsersAccount.accountNumber + " to account number -" + accountsBean.accountNumber);
		    return false;
	}
}

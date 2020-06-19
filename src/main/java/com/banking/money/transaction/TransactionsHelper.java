package com.banking.money.transaction;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;
import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Contains methods to interact with DB and upadate money transaction details
 */
public class TransactionsHelper {
	static final Logger LOGGER = Logger.getLogger(TransactionsHelper.class);
	private static final int MINIMUM_TRANSACTION_ID = 1;
	private static final String TO_ACCOUNT_NUM =  " to account number -";
	
	public boolean performTransaction(Accounts accountsBean, String transactionType, int customerId ) throws DataAccessException, TransactionException{
	        AccountsDaoImpl accountsDaoImpl = ContextBeans.getAcountsDaoImpl();
	        AccountsDaoImpl accountsDaoImplLoggedInUser = ContextBeans.getAcountsDaoImpl();
	        
	        Accounts accountsBeanFromDatabase = accountsDaoImpl.get(accountsBean);
	        if (accountsBeanFromDatabase == null) {
	        	return false;

	        }
	        Accounts loggedInUsersAccount =  accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerId);

	        Transaction transaction = new Transaction();
	        
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
				LOGGER.error(transactionType + " transaction has FAILED on account number -" + loggedInUsersAccount.accountNumber + TO_ACCOUNT_NUM + accountsBean.accountNumber);
				return false;
			}
	        
	        accountsDaoImpl.update(accountsBeanFromDatabase);
	        accountsDaoImplLoggedInUser.update(loggedInUsersAccount);
	        
	        TransactionDaoImpl transactionDaoImpl = ContextBeans.getTransactionDaoImpl();
	        
		    transaction.setTransactionType(transactionType);
		    transaction.setAmount(accountsBean.accountBalance);
		    transaction.setFromAccountAmt(accountsBeanFromDatabase.getAccountBalance());
		    transaction.setToAccountAmt(loggedInUsersAccount.getAccountBalance());
		    if (transactionDaoImpl.save(transaction) >= MINIMUM_TRANSACTION_ID ) {
   			    LOGGER.info(transactionType + " transaction has been successful on account number -" + loggedInUsersAccount.accountNumber + TO_ACCOUNT_NUM + accountsBean.accountNumber);
				return true;

		    }
			LOGGER.error(transactionType + " transaction has FAILED on  account number -" + loggedInUsersAccount.accountNumber + TO_ACCOUNT_NUM + accountsBean.accountNumber);
		    return false;
	}
}

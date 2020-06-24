package com.banking.money.transaction;

import org.apache.log4j.Logger;
import com.banking.exceptions.AccountsDBAccessException;
import com.banking.exceptions.TransactionDBAccessException;
import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Contains methods to interact with DB and upadate money transaction details
 */
public class TransactionsHelper {
	private static final Logger LOGGER = Logger.getLogger(TransactionsHelper.class);
	private static final int MINIMUM_TRANSACTION_ID = 1;
	private static final String TO_ACCOUNT_NUM =  " to account number -";

	public enum transactionStatus {
		OK ("OK"), INVALID_ACCOUNT ("INVALID ACCOUNT NUMBER"), INSUFFICIENT_AMOUNT_ENTERED("INSUFFICIENT AMOUNT"), 
		TRANSACTION_FAILED("TRANSACTION FAILED");
		
		 private String status;

		 transactionStatus(String status) {
		        this.status = status;
		    }

		    public String getStatus() {
		        return status;
		    }
	}
	public transactionStatus performTransaction(final Accounts accountsBean, final String transactionType, final int customerId ) throws TransactionDBAccessException, AccountsDBAccessException{
	        AccountsDaoImpl accountsDaoImpl = ContextBeans.getAcountsDaoImpl();
	        AccountsDaoImpl accountsDaoImplLoggedInUser = ContextBeans.getAcountsDaoImpl();
	        
	        Accounts accountsBeanFromDatabase = accountsDaoImpl.get(accountsBean);
	        if (accountsBeanFromDatabase == null) {
	        	return transactionStatus.INVALID_ACCOUNT;

	        }
	        Accounts loggedInUsersAccount =  accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerId);

	        Transaction transaction = new Transaction();
	        
	        if ( transactionType.equals("credit")) {
	        	if (loggedInUsersAccount.getAccountBalance() >= accountsBean.getAccountBalance()) {
	        	accountsBeanFromDatabase.incrementAccountBalance(accountsBean.getAccountBalance());
	        	loggedInUsersAccount.decrementAccountBalance(accountsBean.getAccountBalance());
	        	transaction.setFromAccount(loggedInUsersAccount.getAccountNumber());
		        transaction.setToAccount(accountsBean.getAccountNumber());
	        	} else {
	        		return transactionStatus.INSUFFICIENT_AMOUNT_ENTERED;
	        	}
	        	
			} else if (transactionType.equals("debit") ) {
				if (accountsBeanFromDatabase.getAccountBalance() >= accountsBean.getAccountBalance()) {
			
				accountsBeanFromDatabase.decrementAccountBalance(accountsBean.getAccountBalance());
				loggedInUsersAccount.incrementAccountBalance(accountsBean.getAccountBalance());
				transaction.setFromAccount(accountsBean.getAccountNumber());
		        transaction.setToAccount(loggedInUsersAccount.getAccountNumber());
				} else {
					return transactionStatus.INSUFFICIENT_AMOUNT_ENTERED;
				}
			}
			else {
				LOGGER.error(transactionType + " transaction has FAILED on account number -" + loggedInUsersAccount.getAccountNumber() + TO_ACCOUNT_NUM + accountsBean.getAccountNumber());
				return transactionStatus.TRANSACTION_FAILED;
			}
	        
	        accountsDaoImpl.update(accountsBeanFromDatabase);
	        accountsDaoImplLoggedInUser.update(loggedInUsersAccount);
	        
	        TransactionDaoImpl transactionDaoImpl = ContextBeans.getTransactionDaoImpl();
	        
		    transaction.setTransactionType(transactionType);
		    transaction.setAmount(accountsBean.getAccountBalance());
		    transaction.setFromAccountAmt(accountsBeanFromDatabase.getAccountBalance());
		    transaction.setToAccountAmt(loggedInUsersAccount.getAccountBalance());
		    if (transactionDaoImpl.save(transaction) >= MINIMUM_TRANSACTION_ID ) {
   			    LOGGER.info(transactionType + " transaction has been successful on account number -" + loggedInUsersAccount.getAccountBalance() + TO_ACCOUNT_NUM + accountsBean.getAccountBalance());
   			    return transactionStatus.OK;
		    }
			LOGGER.error(transactionType + " transaction has FAILED on  account number -" + loggedInUsersAccount.getAccountBalance() + TO_ACCOUNT_NUM + accountsBean.getAccountBalance());
			return transactionStatus.TRANSACTION_FAILED;
	}
}

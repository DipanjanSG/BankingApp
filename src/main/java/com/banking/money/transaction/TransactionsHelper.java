package com.banking.money.transaction;

import org.apache.log4j.Logger;
import com.banking.constants.Constants.TransactionStatus;
import com.banking.exceptions.AccountsDBAccessException;
import com.banking.exceptions.TransactionDBAccessException;
import com.banking.spring.beans.ContextBeansFactory;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Contains methods to interact with DB and upadate money transaction details
 */
public class TransactionsHelper {
	private static final Logger LOGGER = Logger.getLogger(TransactionsHelper.class);
	private static final int MINIMUM_TRANSACTION_ID = 1;
	private static final String TO_ACCOUNT_NUM =  " to account number -";
	private AccountsDaoImpl accountsDaoImpl ;
	private AccountsDaoImpl accountsDaoImplLoggedInUser ;
	private Transaction transaction = new Transaction();
	Account accountsBeanFromDatabase;
	Account loggedInUsersAccount;
	
	/**
	 * @purpose - Performing money transfer from one account to another
	 * @param - 
	 *           accountsBean - Target account (entered by user)
	 *           transactionType - Debit / Credit
	 *           customerId - Logged in user's customerId
	 * @return - TransactionStatus - Enum containing transaction messages 
	 */
	public TransactionStatus performTransaction(final Account accountsBean, final String transactionType, final int customerId ) throws TransactionDBAccessException, AccountsDBAccessException{
	        accountsDaoImpl = ContextBeansFactory.getAcountsDaoImpl();
	        accountsDaoImplLoggedInUser = ContextBeansFactory.getAcountsDaoImpl();
	        
	        accountsBeanFromDatabase = accountsDaoImpl.get(accountsBean.getAccountNumber());
	        if (accountsBeanFromDatabase == null) {
	        	LOGGER.error("Invalid account number entered --> " + accountsBean.getAccountNumber());
	        	return TransactionStatus.INVALID_ACCOUNT;
	        }
	        
	        loggedInUsersAccount =  accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerId);
	        TransactionStatus accountBalUpadationStatus =  updateAccountBalance(  transactionType, accountsBean);
	        if ( accountBalUpadationStatus.getStatus().equals(TransactionStatus.OK.getStatus()) )  {
	        	LOGGER.info("Accounts SUCCESSFULLY update");
	        	accountBalUpadationStatus = updateTransactions(transactionType, accountsBean);
	        }
	        
	        return accountBalUpadationStatus;
	}
	
	/**
	 * @purpose - Performing money transfer (sub step) - Updates account balance
	 * @param - 
	 * 	         transactionType - Debit / Credit
	 *           accountsBean - Target account (entered by user)
	 * @return - TransactionStatus - Enum containing transaction messages 
	 */
	public TransactionStatus updateAccountBalance(final String transactionType, final Account accountsBean) throws AccountsDBAccessException {
		
        if ( transactionType.equals("credit")) {
        	if (loggedInUsersAccount.getAccountBalance() >= accountsBean.getAccountBalance()) {
        		LOGGER.info("Sufficient balance found for crediting");
        	accountsBeanFromDatabase.incrementAccountBalance(accountsBean.getAccountBalance());
        	loggedInUsersAccount.decrementAccountBalance(accountsBean.getAccountBalance());
        	transaction.setFromAccount(loggedInUsersAccount.getAccountNumber());
	        transaction.setToAccount(accountsBean.getAccountNumber());
        	} else {
        		LOGGER.error("Insufficient balance, unable to credit --> " + accountsBean.getAccountBalance());
        		return TransactionStatus.INSUFFICIENT_AMOUNT_ENTERED;
        	}
        	
		} else if (transactionType.equals("debit") ) {
			if (accountsBeanFromDatabase.getAccountBalance() >= accountsBean.getAccountBalance()) {
			LOGGER.info("Sufficient balance found for debiting");
			accountsBeanFromDatabase.decrementAccountBalance(accountsBean.getAccountBalance());
			loggedInUsersAccount.incrementAccountBalance(accountsBean.getAccountBalance());
			transaction.setFromAccount(accountsBean.getAccountNumber());
	        transaction.setToAccount(loggedInUsersAccount.getAccountNumber());
			} else {
				LOGGER.error("Insufficient balance, unable to debit --> " + accountsBean.getAccountBalance());
				return TransactionStatus.INSUFFICIENT_AMOUNT_ENTERED;
			}
		}
		else {
			LOGGER.error(transactionType + " transaction has FAILED on account number -" + loggedInUsersAccount.getAccountNumber() + TO_ACCOUNT_NUM + accountsBean.getAccountNumber());
			return TransactionStatus.TRANSACTION_FAILED;
		}
        
        accountsDaoImpl.update(accountsBeanFromDatabase);
        accountsDaoImplLoggedInUser.update(loggedInUsersAccount);
        return TransactionStatus.OK;
	
	}

	/**
	 * @purpose - Performing money transfer (sub step) - Updates Transactions table
	 * @param - 
	 * 	         transactionType - Debit / Credit
	 *           accountsBean - Target account (entered by user)
	 * @return - TransactionStatus - Enum containing transaction messages 
	 */
public TransactionStatus updateTransactions(final String transactionType, final Account accountsBean) throws TransactionDBAccessException {
	
    TransactionDaoImpl transactionDaoImpl = ContextBeansFactory.getTransactionDaoImpl();
    transaction.setTransactionType(transactionType);
    transaction.setAmount(accountsBean.getAccountBalance());
    transaction.setFromAccountAmt(accountsBeanFromDatabase.getAccountBalance());
    transaction.setToAccountAmt(loggedInUsersAccount.getAccountBalance());
    if (transactionDaoImpl.save(transaction) >= MINIMUM_TRANSACTION_ID ) {
		    LOGGER.info(transactionType + " transaction has been successful on account number -" + loggedInUsersAccount.getAccountBalance() + TO_ACCOUNT_NUM + accountsBean.getAccountBalance());
		    return TransactionStatus.OK;
    }
    
    LOGGER.error(transactionType + " transaction has FAILED on  account number -" + loggedInUsersAccount.getAccountBalance() + TO_ACCOUNT_NUM + accountsBean.getAccountBalance());
	return TransactionStatus.TRANSACTION_FAILED;
	
	}

}

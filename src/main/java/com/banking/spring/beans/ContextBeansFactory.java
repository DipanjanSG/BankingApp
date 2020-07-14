package com.banking.spring.beans;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.banking.account.creation.CustomerDaoImpl;
import com.banking.cc.transactions.authorize.CreditCardHelper;
import com.banking.cc.transactions.authorize.CreditCardTransactionsDaoImpl;
import com.banking.login.CredentialsDaoImpl;
import com.banking.money.transaction.TransactionDaoImpl;
import com.banking.money.transaction.AccountsDaoImpl;
import com.banking.money.transaction.TransactionsHelper;
/**
 * @author Dipanjan Sengupta 
 * @purpose - This class is the used for creating beans, to be called when when we need objects created 
 */
public final class ContextBeansFactory {
	
	private static ApplicationContext context = null;
	private static CreditCardHelper creditCardHelper = null;
	private static CustomerDaoImpl createAccountDao = null;
	private static TransactionsHelper transactionsHelper = null;
	private static CreditCardTransactionsDaoImpl createCreditCardTransactionsDaoBean = null;
	private static TransactionDaoImpl transactionDaoImpl = null;
	private static CredentialsDaoImpl credentialsDaoImpl = null;
	private static final Logger LOGGER = Logger.getLogger(ContextBeansFactory.class);
 
	private ContextBeansFactory() {
		
	}
	
	public static ApplicationContext getContext() {
		if (context == null ) {
			context = new ClassPathXmlApplicationContext("context.xml");
			LOGGER.info("context.xml file initialized for Hibernate beans");
		}
		
		return context;
	}

	public static CreditCardHelper getCreditCardHelper() {
		if (creditCardHelper == null ) {
			creditCardHelper =  (CreditCardHelper)getContext().getBean("creditCardHelperBean");
		}
		
		return creditCardHelper;
	}
	
	public static CustomerDaoImpl getCreateAccountDao() {
		if (createAccountDao == null ) {
			createAccountDao = (CustomerDaoImpl)getContext().getBean("createAccountDaoBean");
		}
		
		return createAccountDao;
	}
	
	public static CreditCardTransactionsDaoImpl getCreateCreditCardTransactionsDao() {
		if (createCreditCardTransactionsDaoBean == null ) {
			createCreditCardTransactionsDaoBean = (CreditCardTransactionsDaoImpl)getContext().getBean("createCreditCardTransactionsDaoBean");
		}
		
		return createCreditCardTransactionsDaoBean;
	}
	
	public static TransactionsHelper getTransactionsHelper() {
		if (transactionsHelper == null ) {
			transactionsHelper =  (TransactionsHelper)getContext().getBean("createTransactionsHelperBean");
		}
		
		return transactionsHelper;
	}
	public static AccountsDaoImpl getAcountsDaoImpl() {		
		return (AccountsDaoImpl)getContext().getBean("accountsDaoImplBean");
	}
	
	public static TransactionDaoImpl getTransactionDaoImpl() {
		if (transactionDaoImpl == null ) {
			transactionDaoImpl =  (TransactionDaoImpl)getContext().getBean("transactionDaoImpl");
		}
		
		return transactionDaoImpl;
	}

	public static CredentialsDaoImpl getCredentialsDaoImpl() {
		if (credentialsDaoImpl == null ) {
			credentialsDaoImpl =  (CredentialsDaoImpl)getContext().getBean("credentialsDaoImpl");
		}
		
		return credentialsDaoImpl;
	}
}

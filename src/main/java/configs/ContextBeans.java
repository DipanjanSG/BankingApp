package configs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import AuthorizeCCTransactions.CreditCard;
import AuthorizeCCTransactions.CreditCardHelper;
import AuthorizeCCTransactions.CreditCardTransactionsDaoImpl;
import CreateAccount.CreateAccount;
import CreateAccount.CustomerDaoImpl;
import CreateAccount.Customer;
import CreateAccount.CustomerDao;
import Login.Credentials;
import Login.LoginBean;
import TransactionDetails.Transaction;
import TransactionDetails.TransactionDaoImpl;
import Transactions.Accounts;
import Transactions.AccountsDaoImpl;
import Transactions.TransactionsDao;

public class ContextBeans {
	
	private static ApplicationContext context = null;
	private static Transaction transactionBean = null;
	private static Customer customerBean = null;
	private static CreditCard creditCardBean = null;
	private static CreateAccount createAccount = null;
	private static LoginBean loginBean = null;
	private static Accounts accountsBean = null;
	private static Credentials credentials = null;
	private static CreditCardHelper creditCardHelper = null;
	private static CustomerDaoImpl createAccountDao = null;
	private static TransactionsDao transactionsDao = null;
	private static CreditCardTransactionsDaoImpl createCreditCardTransactionsDaoBean = null;
	private static AccountsDaoImpl accountsDaoImpl = null;
	private static TransactionDaoImpl transactionDaoImpl = null;
	private static SessionFactory sFactory = null ;
	private static Session session = null;
	 
	public static ApplicationContext getContext() {
		if (context == null ) {
			context = new ClassPathXmlApplicationContext("configs/context.xml");
		}
		
		return context;
	}
	
	public static Credentials getCredentials() {
		if (credentials == null ) {
			credentials = (Credentials)getContext().getBean("credentials");
		}
		return credentials;
	}

	public static Transaction getTransactionDetailsBean() {
		if (transactionBean == null ) {
	        transactionBean = (Transaction)getContext().getBean("transactionBean");
		}
		
		return transactionBean;
	}
	
	public static CreditCard getCreditCardBean() {
		if (creditCardBean == null ) {
			creditCardBean = (CreditCard)getContext().getBean("creditCardBean");
		}
		
		return creditCardBean;
	}

	public static CreateAccount getCreateAccount() {
		if (createAccount == null ) {
			createAccount = (CreateAccount)getContext().getBean("createAccount");
		}
		
		return createAccount;
	}

	public static LoginBean getLoginBean() {
		if (loginBean == null ) {
			loginBean = (LoginBean)getContext().getBean("loginBean");
		}
		return loginBean;
	}


	public static Accounts getAccountsBean() {
		if (accountsBean == null ) {
			accountsBean = (Accounts)getContext().getBean("accountsBean");
		}
		return accountsBean;
	}
	
	public static Customer getCustomerBean() {
		if (customerBean == null ) {
			customerBean =  (Customer)getContext().getBean("customerBean");
		}
		
		return customerBean;
	}

	public static Customer getNewCustomerBean() {
		
		customerBean =  (Customer)getContext().getBean("customerBean");
	
		
		return customerBean;
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
	
	public static CreditCardTransactionsDaoImpl getcreateCreditCardTransactionsDao() {
		if (createCreditCardTransactionsDaoBean == null ) {
			createCreditCardTransactionsDaoBean = (CreditCardTransactionsDaoImpl)getContext().getBean("createCreditCardTransactionsDaoBean");
		}
		
		return createCreditCardTransactionsDaoBean;
	}
	
	public static TransactionsDao getTransactionsDao() {
		if (transactionsDao == null ) {
			transactionsDao =  (TransactionsDao)getContext().getBean("createTransactionsDaoBean");
		}
		
		return transactionsDao;
	}
	public static AccountsDaoImpl getAcountsDaoImpl() {
		if (accountsDaoImpl == null ) {
			accountsDaoImpl =  (AccountsDaoImpl)getContext().getBean("accountsDaoImplBean");
		}
		
		return accountsDaoImpl;
	}
	
	public static TransactionDaoImpl getTransactionDaoImpl() {
		if (transactionDaoImpl == null ) {
			transactionDaoImpl =  (TransactionDaoImpl)getContext().getBean("transactionDaoImpl");
		}
		
		return transactionDaoImpl;
	}

}

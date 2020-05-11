package configs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import AuthorizeCCTransactions.CreditCardBean;
import CreateAccount.CreateAccount;
import CreateAccount.CustomerBean;
import Login.Credentials;
import Login.LoginBean;
import TransactionDetails.TransactionDetailsBean;
import Transactions.AccountsBean;

public class ContextBeans {
	
	private static ApplicationContext context = null;
	private static TransactionDetailsBean transactionDetailsBean = null;
	private static CustomerBean customerBean = null;
	private static CreditCardBean creditCardBean = null;
	private static CreateAccount createAccount = null;
	private static LoginBean loginBean = null;
	private static AccountsBean accountsBean = null;
	private static Credentials credentials = null;
	
	
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

	public static TransactionDetailsBean getTransactionDetailsBean() {
		if (transactionDetailsBean == null ) {
	        transactionDetailsBean = (TransactionDetailsBean)getContext().getBean("transactionDetailsBean");
		}
		
		return transactionDetailsBean;
	}
	
	public static CreditCardBean getCreditCardBean() {
		if (creditCardBean == null ) {
			creditCardBean = (CreditCardBean)getContext().getBean("creditCardBean");
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


	public static AccountsBean getAccountsBean() {
		if (accountsBean == null ) {
			accountsBean = (AccountsBean)getContext().getBean("accountsBean");
		}
		return accountsBean;
	}
	
	public static CustomerBean getCustomerBean() {
		if (customerBean == null ) {
			customerBean =  (CustomerBean)getContext().getBean("customerBean");
		}
		
		return customerBean;
	}
	
	public static CustomerBean getNewCustomerBean() {
		
		customerBean =  (CustomerBean)getContext().getBean("customerBean");
	
		
		return customerBean;
	}
}

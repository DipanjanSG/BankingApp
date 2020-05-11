package Transactions;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import AuthorizeCCTransactions.CreditCardBean;
import BusinessLogic.SessionFactoryCreation;
import Login.Credentials;
import Login.LoginBean;
import TransactionDetails.TransactionDetailsBean;
import configs.ContextBeans;
import configs.HibernateUtils;

public class TransactionsDao {

	public boolean performTransaction(AccountsBean accountsBean, String transactionType, int loggedInUsersAccountNumber) throws ClassNotFoundException {
		
	        boolean hasRecordBeenFound = false;
	        
	        SessionFactory sFactory = HibernateUtils.getSessionFactory();
	 
	   	    Session session = sFactory.openSession();
	       
	        Transaction tx = session.beginTransaction();	        
	        AccountsBean accountsBeanFromDatabase = session.get(AccountsBean.class, new Integer(accountsBean.accountNumber));
	        AccountsBean loggednInUsersaccounts = session.get(AccountsBean.class, new Integer(loggedInUsersAccountNumber));
	       
	        double oldAccountBalance = accountsBeanFromDatabase.getAccountBalance();
	        if ( transactionType.equals("credit") ) {
	        	accountsBeanFromDatabase.incrementAccountBalance(accountsBean.accountBalance);
	        	loggednInUsersaccounts.decrementAccountBalance(accountsBean.accountBalance);
			} else {
				accountsBeanFromDatabase.decrementAccountBalance(accountsBean.accountBalance);
				loggednInUsersaccounts.incrementAccountBalance(accountsBean.accountBalance);				
			}
	        
	        session.update(accountsBeanFromDatabase);
	        session.update(loggednInUsersaccounts);
	        tx.commit();
	        
	        if ( session.getTransaction().getStatus().toString() == "COMMITTED") {
		        tx = session.beginTransaction();
		        
		        TransactionDetailsBean transactionDetailsBean = ContextBeans.getTransactionDetailsBean();
		        transactionDetailsBean.setFromAccount(accountsBean.accountNumber);
		        transactionDetailsBean.setToAccount(loggedInUsersAccountNumber);
		        transactionDetailsBean.setTransactionType(transactionType);
		        
		        transactionDetailsBean.setAmount(accountsBean.accountBalance);
		        transactionDetailsBean.setFromAccountAmt(accountsBeanFromDatabase.accountBalance);
		        transactionDetailsBean.setToAccountAmt(loggednInUsersaccounts.accountBalance);
		        session.save(transactionDetailsBean);
		        tx.commit();
	        }
	                
	        session.close();
	        sFactory.close();
		return true;
	}

	public int getAccountNumber(int customerId) throws ClassNotFoundException {
		
			Session session = SessionFactoryCreation.getSessionInstance();
        	String hql = "from Transactions.AccountsBean where customerId = :customerId";
			TypedQuery<AccountsBean> query = session.createQuery(hql);
			query.setParameter("customerId",customerId);
			List <AccountsBean> retrievedCredentials = query.getResultList();
			
			if ( retrievedCredentials.size() == 1 ) {
				return retrievedCredentials.get(0).getAccountNumber();
			} 
	        return 0;
     }
}

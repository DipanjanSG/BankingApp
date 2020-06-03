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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import AuthorizeCCTransactions.CreditCard;
import BusinessLogic.SessionFactoryCreation;
import Login.Credentials;
import Login.LoginBean;
import TransactionDetails.Transaction;
import TransactionDetails.TransactionDaoImpl;
import configs.ContextBeans;
import configs.HibernateUtils;

public class TransactionsDao {

	public boolean performTransaction(Accounts accountsBean, String transactionType, int loggedInUsersAccountNumber) throws ClassNotFoundException {
	        AccountsDaoImpl accountsDaoImpl = ContextBeans.getAcountsDaoImpl();
	        Transaction transaction = ContextBeans.getTransactionDetailsBean();
	        Accounts accountsBeanFromDatabase = accountsDaoImpl.get(accountsBean);
	        Accounts loggednInUsersaccounts = accountsDaoImpl.get(accountsBean);
	        double oldAccountBalance = accountsBeanFromDatabase.getAccountBalance();
	        if ( transactionType.equals("credit") ) {
	        	accountsBeanFromDatabase.incrementAccountBalance(accountsBean.accountBalance);
	        	loggednInUsersaccounts.decrementAccountBalance(accountsBean.accountBalance);
	        	transaction.setFromAccount(loggedInUsersAccountNumber);
		        transaction.setToAccount(accountsBean.accountNumber);
			} else {
				accountsBeanFromDatabase.decrementAccountBalance(accountsBean.accountBalance);
				loggednInUsersaccounts.incrementAccountBalance(accountsBean.accountBalance);
				transaction.setFromAccount(accountsBean.accountNumber);
		        transaction.setToAccount(loggedInUsersAccountNumber);
			}
	        
	        accountsDaoImpl.update(accountsBeanFromDatabase);
	        accountsDaoImpl.update(loggednInUsersaccounts);
	        
	        TransactionDaoImpl transactionDaoImpl = ContextBeans.getTransactionDaoImpl();
	        
		    transaction.setTransactionType(transactionType);
		    transaction.setAmount(accountsBean.accountBalance);
		    transaction.setFromAccountAmt(accountsBeanFromDatabase.accountBalance);
		    transaction.setToAccountAmt(loggednInUsersaccounts.accountBalance);
		    transactionDaoImpl.save(transaction);

		return true;
	}

	public int getAccountNumber(int customerId) throws ClassNotFoundException {
		
			Session session = SessionFactoryCreation.getSessionInstance();
        	String hql = "from Transactions.Accounts where customerId = :customerId";
			TypedQuery<Accounts> query = session.createQuery(hql);
			query.setParameter("customerId",customerId);
			List <Accounts> retrievedCredentials = query.getResultList();
			
			if ( retrievedCredentials.size() == 1 ) {
				return retrievedCredentials.get(0).getAccountNumber();
			} 
	        return 0;
     }
}

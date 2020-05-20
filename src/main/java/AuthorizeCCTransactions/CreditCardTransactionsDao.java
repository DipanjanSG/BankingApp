package AuthorizeCCTransactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import BusinessLogic.SessionFactoryCreation;
import CreateAccount.Customer;
import Login.LoginBean;
import configs.HibernateUtils;

public class CreditCardTransactionsDao {
	
	public CreditCardBean validate(CreditCardBean creditCardBeanDetails ) throws ClassNotFoundException {
        boolean hasRecordBeenFound = false;
        Session session = SessionFactoryCreation.getSessionInstance();
 
        Transaction tx = session.beginTransaction();
        
        CreditCardBean creditCardDataBase = session.get(CreditCardBean.class, new String(creditCardBeanDetails.creditCardNumber));
        
       
        tx.commit();
        
        return creditCardDataBase;
        
	}

	public void update(CreditCardBean newCreditCardDetails ) throws ClassNotFoundException {
        boolean hasRecordBeenFound = false;
        Session session = SessionFactoryCreation.getSessionInstance();
        session.clear();
        Transaction tx = session.beginTransaction();
        
        session.update(newCreditCardDetails);
        
       
        tx.commit();

       
	}

	
	
	
	
	

}

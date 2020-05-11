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
import CreateAccount.CustomerBean;
import configs.HibernateUtils;

public class CustomersDao {
	public CustomerBean fetchRecordWithUserName(CustomerBean customerBean) throws ClassNotFoundException {
                
		Session session = SessionFactoryCreation.getSessionInstance();
        
        Transaction tx = session.beginTransaction();
        CustomerBean retrivedCustomerBean = (CustomerBean)session.get(CustomerBean.class, new Integer(customerBean.getCreditCardBean().getCardOwner()));
        
        tx.commit();
        
	    return retrivedCustomerBean;
	}
	
}

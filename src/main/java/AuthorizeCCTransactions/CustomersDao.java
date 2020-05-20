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
import configs.HibernateUtils;

public class CustomersDao {
	public Customer fetchRecordWithUserName(Customer customerBean) throws ClassNotFoundException {
                
		Session session = SessionFactoryCreation.getSessionInstance();
        
        Transaction tx = session.beginTransaction();
        Customer retrivedCustomerBean = (Customer)session.get(Customer.class, new Integer(customerBean.getCreditCardBean().getCardOwner()));
        
        tx.commit();
        
	    return retrivedCustomerBean;
	}
	
}

package CreateAccount;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import BusinessLogic.SessionFactoryCreation;
import Login.Credentials;
import configs.HibernateUtils;

public class CreateAccountDao {
	public boolean validate(CustomerBean createAccountBeanDetails) throws ClassNotFoundException {
                
		Session session = SessionFactoryCreation.getSessionInstance();
        
        Transaction tx = session.beginTransaction();
        session.save(createAccountBeanDetails);
        
        tx.commit();
        
	    return true;
	}
	
}

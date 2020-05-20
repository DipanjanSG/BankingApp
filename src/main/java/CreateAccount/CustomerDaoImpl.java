package CreateAccount;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate.HibernateTemplate;

import BusinessLogic.SessionFactoryCreation;
import Login.Credentials;
import configs.HibernateUtils;

public class CustomerDaoImpl implements CustomerDao{
	
	private HibernateTemplate hibernateTemplate;
	
	public boolean createNewAccount(Customer createAccountBeanDetails) throws ClassNotFoundException {
                
		Session session = SessionFactoryCreation.getSessionInstance();
        
        Transaction tx = session.beginTransaction();
        session.save(createAccountBeanDetails);
        
        tx.commit();
        
	    return true;
	}

	public void save(Customer customer) {
		hibernateTemplate.save(customer);
		
	}

	public void update(Customer customer) {
		hibernateTemplate.update(customer);
		
	}

	public void delete(Customer customer) {
		hibernateTemplate.delete(customer);
	}

	public List<Customer> getCustomers() {
		return hibernateTemplate.loadAll(Customer.class);
	}

	
	
}

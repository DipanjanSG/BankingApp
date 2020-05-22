package CreateAccount;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.*;
import BusinessLogic.SessionFactoryCreation;

public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public boolean createNewAccount(Customer createAccountBeanDetails) throws ClassNotFoundException {
                
		Session session = SessionFactoryCreation.getSessionInstance();
        
        Transaction tx = session.beginTransaction();
        session.save(createAccountBeanDetails);
        
        tx.commit();
        
	    return true;
	}

	public void save(Customer customer) {
		hibernateTemplate.save(customer).toString();

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

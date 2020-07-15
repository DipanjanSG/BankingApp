package com.banking.account.creation;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import com.banking.exceptions.CustomerDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - HibernateTemplate for operations on customer table
 */
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static final Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class);
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public Integer save(Customer customer) throws CustomerDBAccessException {
		
		try {
			return (Integer)hibernateTemplate.save(customer);
		} catch (TransactionException ex) {
			String expMsg = "Was Unable to save customer --> " + customer.getUserName();
			LOGGER.error(ex + " " + expMsg);
			throw new CustomerDBAccessException(expMsg);
		}
		
		
	}
	
	@Transactional(readOnly = false)
	public void update(Customer customer) throws CustomerDBAccessException {
		try {
			hibernateTemplate.update(customer);
		} catch (TransactionException ex) {
			String expMsg = "Was Unable to update customer --->" + customer.toString();
			LOGGER.error(ex + " " + expMsg);
			throw new CustomerDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(int customerId) throws CustomerDBAccessException {
  
		try {
			hibernateTemplate.delete(get(customerId));
		} catch (TransactionException ex) {
			String expMsg = "Was Unable to delete customer ----> " + customerId;
			LOGGER.error(ex + " " + expMsg);
			throw new CustomerDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = true)
	public List<Customer> getCustomers() throws CustomerDBAccessException {
		try {
			return hibernateTemplate.loadAll(Customer.class);
		} catch (DataAccessException ex) {
			String expMsg = "Was Unable to retrieve all customers ---->";
			LOGGER.error(ex + " " + expMsg);
			throw new CustomerDBAccessException(expMsg);
		}
	}

	@Transactional(readOnly = true)
	public Customer get(int customerId) throws CustomerDBAccessException {

		try {
			return hibernateTemplate.get(Customer.class, customerId);
		} catch (DataAccessException ex) {
			String expMsg = "Was Unable to retrieve all customer ---->" + customerId;
			LOGGER.error(ex + " " + expMsg);
			throw new CustomerDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = true)
	public List<Customer> getCustomerWithParam(String userName, String emailId) throws CustomerDBAccessException {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

		    criteria.add(Restrictions.eq("userName", userName));
		    criteria.add(Restrictions.eq("emailId", emailId));
		    return ((List<Customer>) hibernateTemplate.findByCriteria(criteria));
		 	} catch (DataAccessException ex ) {
		 		String expMsg = "Unable to get credentials with parameters";
				LOGGER.error(ex + " " + expMsg);
				throw new CustomerDBAccessException(expMsg);
			}
	}
	

}

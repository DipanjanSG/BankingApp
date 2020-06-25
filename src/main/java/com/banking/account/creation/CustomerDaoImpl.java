package com.banking.account.creation;


import java.util.List;
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
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public Integer save(Customer customer) throws CustomerDBAccessException {
		
		try {
			return (Integer)hibernateTemplate.save(customer);
		} catch (TransactionException ex) {
			throw new CustomerDBAccessException("Was Unable to save customer --> " + customer.getUserName());
		}
		
		
	}
	
	@Transactional(readOnly = false)
	public void update(Customer customer) throws CustomerDBAccessException {
		try {
			hibernateTemplate.update(customer);
		} catch (TransactionException ex) {
			throw new CustomerDBAccessException("Was Unable to update customer --->" + customer.toString());
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Customer customer) throws CustomerDBAccessException {
		try {
			hibernateTemplate.delete(customer);
		} catch (TransactionException ex) {
			throw new CustomerDBAccessException("Was Unable to delete customer ---->" + customer.toString());
		}
	}
	
	@Transactional(readOnly = false)
	public List<Customer> getCustomers() throws CustomerDBAccessException {
		try {
			return hibernateTemplate.loadAll(Customer.class);
		} catch (DataAccessException ex) {
			throw new CustomerDBAccessException("Was Unable to retrieve all customers ---->");
		}
	}

	@Transactional(readOnly = false)
	public Customer get(Customer customer) throws CustomerDBAccessException {
		try {
			return hibernateTemplate.get(Customer.class, customer.getCustomerId());
		} catch (DataAccessException ex) {
			throw new CustomerDBAccessException("Was Unable to retrieve all customer ---->" + customer.toString());
		}
	}
	

}

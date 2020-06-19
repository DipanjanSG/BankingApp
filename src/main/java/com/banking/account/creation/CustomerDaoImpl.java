package com.banking.account.creation;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.banking.money.transaction.Accounts;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on customer table
 */
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public Integer save(Customer customer) throws TransactionException {
		return (Integer)hibernateTemplate.save(customer);
		
	}
	
	@Transactional(readOnly = false)
	public void update(Customer customer) throws DataAccessException {
		hibernateTemplate.update(customer);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(Customer customer) throws DataAccessException {
		hibernateTemplate.delete(customer);
	}
	
	@Transactional(readOnly = false)
	public List<Customer> getCustomers() throws DataAccessException {
		return hibernateTemplate.loadAll(Customer.class);
	}

	@Transactional(readOnly = false)
	public Customer get(Customer customer) throws DataAccessException {
		return hibernateTemplate.get(Customer.class, customer.getCustomerId());
	}
	

}

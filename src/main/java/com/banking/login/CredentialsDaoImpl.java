package com.banking.login;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.banking.account.creation.Customer;
import com.banking.exceptions.CredentialsDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - HibernateTemplate for operations on credentials table
 */
public class CredentialsDaoImpl {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = false)
	public Credentials get(Customer customer) throws CredentialsDBAccessException{
		try {
		return hibernateTemplate.get(Credentials.class, customer.getCustomerId());
		} catch (DataAccessException ex ) {
			throw new CredentialsDBAccessException("Unable to get new credentials");
		}
	}

	@Transactional(readOnly = false)
	public void save(Credentials credential) throws CredentialsDBAccessException {
		try {
			hibernateTemplate.save(credential);
		} catch (DataAccessException ex ) {
			throw new CredentialsDBAccessException("Unable to get save credentials");
		}
	}
	
	@Transactional(readOnly = false)
	public void update(Credentials credential) throws CredentialsDBAccessException {
		try {
			hibernateTemplate.update(credential);
		} catch (DataAccessException ex ) {
			throw new CredentialsDBAccessException("Unable to get update credentials");
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(Credentials credential) throws CredentialsDBAccessException {
		try {
			hibernateTemplate.delete(credential);
		} catch (DataAccessException ex ) {
			throw new CredentialsDBAccessException("Unable to delete new credentials");
		}
	}
	
	@Transactional(readOnly = false) 
	public List<Credentials> getcredentials() throws CredentialsDBAccessException {
		try {
			return hibernateTemplate.loadAll(Credentials.class);
		} catch (DataAccessException ex ) {
			throw new CredentialsDBAccessException("Unable to get all credentials");
		}
	}

    public List<Credentials> getCredentialDetails( Credentials credentials ) throws CredentialsDBAccessException {
    	try {
		DetachedCriteria criteria = DetachedCriteria.forClass(Credentials.class);
	    criteria.add(Restrictions.eq("userName", credentials.getUserName()));
	    criteria.add(Restrictions.eq("password", credentials.getPassword()));
	    return ((List<Credentials>) hibernateTemplate.findByCriteria(criteria));
	 	} catch (DataAccessException ex ) {
			throw new CredentialsDBAccessException("Unable to get credentials with parameters");
		}
	}
}

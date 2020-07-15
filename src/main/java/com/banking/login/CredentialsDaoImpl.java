package com.banking.login;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.banking.exceptions.CredentialsDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - HibernateTemplate for operations on credentials table
 */
public class CredentialsDaoImpl implements CredentialsDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static final Logger LOGGER = Logger.getLogger(CredentialsDaoImpl.class);

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = true)
	public Credentials get(int customerId) throws CredentialsDBAccessException{
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Credentials.class);
			criteria.add(Restrictions.eq("customerId", customerId));
			return ((List<Credentials>)hibernateTemplate.findByCriteria(criteria)).get(0);

		} catch (DataAccessException ex ) {
			String expMsg = "Unable to get new credentials";
			LOGGER.error(ex + " " + expMsg);
			throw new CredentialsDBAccessException(expMsg);
		}
	}

	@Transactional(readOnly = false)
	public void save(Credentials credential) throws CredentialsDBAccessException {
		try {
			hibernateTemplate.save(credential);
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to save credentials";
			LOGGER.error(ex + " " + expMsg);
			throw new CredentialsDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = false)
	public void update(Credentials credential) throws CredentialsDBAccessException {
		try {
			hibernateTemplate.update(credential);
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to update credentials";
			LOGGER.error(ex + " " + expMsg);
			throw new CredentialsDBAccessException(expMsg);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(int customerId) throws CredentialsDBAccessException {
		try {
			hibernateTemplate.delete(get(customerId));
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to delete credentials";
			LOGGER.error(ex + " " + expMsg);
			throw new CredentialsDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = true) 
	public List<Credentials> getCredentials() throws CredentialsDBAccessException {
		try {
			return hibernateTemplate.loadAll(Credentials.class);
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to get all credentials";
			LOGGER.error(ex + " " + expMsg);
			throw new CredentialsDBAccessException(expMsg);
		}
	}

	@Transactional(readOnly = true)
    public List<Credentials> getCredentialDetails( String userName, String password ) throws CredentialsDBAccessException {
    	try {
		DetachedCriteria criteria = DetachedCriteria.forClass(Credentials.class);
	    criteria.add(Restrictions.eq("userName", userName));
	    criteria.add(Restrictions.eq("password", password));
	    return ((List<Credentials>) hibernateTemplate.findByCriteria(criteria));
	    
	 	} catch (DataAccessException ex ) {
	 		String expMsg = "Unable to get credentials with parameters";
			LOGGER.error(ex + " " + expMsg);
			throw new CredentialsDBAccessException(expMsg);
		}
	}

}

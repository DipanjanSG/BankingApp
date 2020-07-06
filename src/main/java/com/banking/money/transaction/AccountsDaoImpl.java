package com.banking.money.transaction;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.banking.account.creation.Customer;
import com.banking.exceptions.AccountsDBAccessException;
import com.banking.login.CredentialsDaoImpl;

/**
 * @author Dipanjan Sengupta 
 * @purpose - HibernateTemplate for operations on accounts table
 */
public class AccountsDaoImpl implements AccountsDao {

	private static final int RETRIVED_LIST_INDEX = 0;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	private static final Logger LOGGER = Logger.getLogger(AccountsDaoImpl.class);

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = true)
	public Accounts get(int accountNumber) throws AccountsDBAccessException{
		try {
		return hibernateTemplate.get(Accounts.class, accountNumber);
		}  catch (DataAccessException ex ) {
			String expMsg = "Unable to get account ";
			LOGGER.error(ex + " " + expMsg);
			throw new AccountsDBAccessException(expMsg);
		}
	}
    	
	@Transactional(readOnly = false)
	public void save(Accounts accountsBean) throws AccountsDBAccessException {
		try {
		hibernateTemplate.save(accountsBean);
		}  catch (DataAccessException ex ) {
			String expMsg = "Unable to save Account";
			LOGGER.error(ex + " " + expMsg);
			throw new AccountsDBAccessException(expMsg);
		}
	}

	@Transactional(readOnly = false)
	public void update(Accounts accountsBean) throws AccountsDBAccessException{
		try {
		hibernateTemplate.update(accountsBean);
		}  catch (DataAccessException ex ) {
			String expMsg = "Unable to update Account";
			LOGGER.error(ex + " " + expMsg);
			throw new AccountsDBAccessException(expMsg);
		}
	}

	@Transactional(readOnly = false)
	public void delete(int accountNumber) throws AccountsDBAccessException {
		try {hibernateTemplate.delete(get(accountNumber));
		}  catch (DataAccessException ex ) {
			String expMsg = "Unable to delete Account";
			LOGGER.error(ex + " " + expMsg);
			throw new AccountsDBAccessException(expMsg);
		}
		
		
	}

	@Transactional(readOnly = false)
	public List<Accounts> getAllAccounts() throws AccountsDBAccessException {
		try {return hibernateTemplate.loadAll(Accounts.class);
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to get all Accounts";
			LOGGER.error(ex + " " + expMsg);
			throw new AccountsDBAccessException(expMsg);
		}
	}

	@Transactional(readOnly = true)
    public Accounts getAccountWithCustomerId(int customerId) throws AccountsDBAccessException {
    	try {
    	Customer customer = new Customer();
        customer.setCustomerId(customerId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Accounts.class);
		criteria.add(Restrictions.eq("customerBean", customer));
		
        return ((List<Accounts>) hibernateTemplate.findByCriteria(criteria)).get(RETRIVED_LIST_INDEX);
    	} catch (DataAccessException ex ) {
    		String expMsg = "Unable to get Account with customerId";
			LOGGER.error(ex + " " + expMsg);
			throw new AccountsDBAccessException(expMsg);
		}
	}

}

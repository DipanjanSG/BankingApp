package com.banking.money.transaction;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.banking.account.creation.Customer;
import com.banking.exceptions.AccountsDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - HibernateTemplate for operations on accounts table
 */
public class AccountsDaoImpl implements AccountsDao{

	private static final int RETRIVED_LIST_INDEX = 0;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = false)
	public Accounts get(Accounts accountsBean) throws AccountsDBAccessException{
		try {
		return hibernateTemplate.get(Accounts.class, accountsBean.getAccountNumber());
		}  catch (DataAccessException ex ) {
			throw new AccountsDBAccessException("Unable to delete get account details");
		}
	}
    	
	@Transactional(readOnly = false)
	public void save(Accounts accountsBean) throws AccountsDBAccessException {
		try {
		hibernateTemplate.save(accountsBean);
		}  catch (DataAccessException ex ) {
			throw new AccountsDBAccessException("Unable to save Account");
		}
	}

	@Transactional(readOnly = false)
	public void update(Accounts accountsBean) throws AccountsDBAccessException{
		try {
		hibernateTemplate.update(accountsBean);
		}  catch (DataAccessException ex ) {
			throw new AccountsDBAccessException("Unable to update Account");
		}
	}

	@Transactional(readOnly = false)
	public void delete(Accounts accountsBean) throws AccountsDBAccessException {
		try {hibernateTemplate.delete(accountsBean);
		}  catch (DataAccessException ex ) {
			throw new AccountsDBAccessException("Unable to delete Account");
		}
		
		
	}

	@Transactional(readOnly = false)
	public List<Accounts> getAllAccounts() throws AccountsDBAccessException {
		try {return hibernateTemplate.loadAll(Accounts.class);
		} catch (DataAccessException ex ) {
			throw new AccountsDBAccessException("Unable to get all Accounts");
		}
	}

    public Accounts getAccountWithCustomerId(int customerId) throws AccountsDBAccessException {
    	try {
    	Customer customer = new Customer();
        customer.setCustomerId(customerId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Accounts.class);
		criteria.add(Restrictions.eq("customerBean", customer));
		
        return ((List<Accounts>) hibernateTemplate.findByCriteria(criteria)).get(RETRIVED_LIST_INDEX);
    	} catch (DataAccessException ex ) {
			throw new AccountsDBAccessException("Unable to get Account with customerId");
		}
	}

}

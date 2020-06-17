package com.banking.money.transaction;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import com.banking.account.creation.Customer;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on accounts table
 */
public class AccountsDaoImpl implements AccountsDao{

	private static final int RETRIVED_LIST_INDEX = 0;
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = false)
	public Accounts get(Accounts accountsBean) throws DataAccessException, TransactionException{
		return hibernateTemplate.get(Accounts.class, accountsBean.accountNumber);
	}
    	
	@Transactional(readOnly = false)
	public void save(Accounts accountsBean) throws DataAccessException, TransactionException {
		hibernateTemplate.save(accountsBean);		
	}

	@Transactional(readOnly = false)
	public void update(Accounts accountsBean) throws DataAccessException, TransactionException{
		hibernateTemplate.update(accountsBean);
	}

	@Transactional(readOnly = false)
	public void delete(Accounts accountsBean) throws DataAccessException, TransactionException{
		hibernateTemplate.delete(accountsBean);
		
	}

	@Transactional(readOnly = false)
	public List<Accounts> getAllAccounts() throws DataAccessException, TransactionException{
		return hibernateTemplate.loadAll(Accounts.class);
	}

    public Accounts getAccountWithCustomerId(int customerId) throws DataAccessException {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Accounts.class);
		criteria.add(Restrictions.eq("customerBean", customer));
		
        return ((List<Accounts>) hibernateTemplate.findByCriteria(criteria)).get(RETRIVED_LIST_INDEX);
	}

}

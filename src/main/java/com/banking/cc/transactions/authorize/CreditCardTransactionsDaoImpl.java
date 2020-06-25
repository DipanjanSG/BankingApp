package com.banking.cc.transactions.authorize;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.banking.exceptions.CreditCardDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - HibernateTemplate for operations on credit_card table
 */
public class CreditCardTransactionsDaoImpl implements CreditCardTransactions{

	@Autowired
	private HibernateTemplate hibernateTemplate;
		
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public CreditCard get(CreditCard creditCardBean) throws CreditCardDBAccessException {
		try {
		return hibernateTemplate.get(CreditCard.class, creditCardBean.getCreditCardNumber());
		} catch(DataAccessException ex) {
			throw new CreditCardDBAccessException("Unable to get credit card details for ---->" + creditCardBean.toString());
		}
	}
	
	@Transactional(readOnly = false)
	public void save(CreditCard creditCardBean) throws CreditCardDBAccessException {
		try {
			hibernateTemplate.save(creditCardBean);
		} catch(DataAccessException ex) {
			throw new CreditCardDBAccessException("Unable to save credit card details for ---->" + creditCardBean.toString());
		}

	}
	
	@Transactional(readOnly = false)
	public void update(CreditCard creditCardBean) throws CreditCardDBAccessException {
		try {
			hibernateTemplate.update(creditCardBean);
		} catch(DataAccessException ex) {
			throw new CreditCardDBAccessException("Unable to update credit card details for ---->" + creditCardBean.toString());
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(CreditCard creditCardBean) throws CreditCardDBAccessException {
		try {
		hibernateTemplate.delete(creditCardBean);
		} catch(DataAccessException ex) {
			throw new CreditCardDBAccessException("Unable to delete credit card details for ---->" + creditCardBean.toString());
		}
	}
	
	@Transactional(readOnly = false)
	public List<CreditCard> getAllCreditCards() throws CreditCardDBAccessException {
		try {
		return hibernateTemplate.loadAll(CreditCard.class);
		} catch(DataAccessException ex) {
			throw new CreditCardDBAccessException("Unable to fetch all credit card details");
		}
	}
	
    public List<CreditCard> getCreditCardWithParam(CreditCard creditCardBean) throws CreditCardDBAccessException {
    	try {
		DetachedCriteria criteria = DetachedCriteria.forClass(CreditCard.class);
		criteria.add(Restrictions.eq("cvvCode", creditCardBean.getCvvCode()));
		criteria.add(Restrictions.eq("creditCardNumber", creditCardBean.getCreditCardNumber()));

		return (List<CreditCard>)hibernateTemplate.findByCriteria(criteria);
		
    	} catch(DataAccessException ex) {
			throw new CreditCardDBAccessException("Unable to fetch credit card with parameter --->" + creditCardBean.toString());
		}
    	
	}
}

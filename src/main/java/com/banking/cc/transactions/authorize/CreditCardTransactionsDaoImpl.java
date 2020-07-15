package com.banking.cc.transactions.authorize;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
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
		
	private static final Logger LOGGER = Logger.getLogger(CreditCardTransactionsDaoImpl.class);

	private static final String CREDIT_CARD_COL_NAME = "creditCardNumber";
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = true)
	public CreditCard get(String creditCardNumber) throws CreditCardDBAccessException {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CreditCard.class);
			criteria.add(Restrictions.eq(CREDIT_CARD_COL_NAME, creditCardNumber));
			return ((List<CreditCard>)hibernateTemplate.findByCriteria(criteria)).get(0);

			} catch(DataAccessException ex) {
			String expMsg = "Unable to get credit card details for ---->" + creditCardNumber;
			LOGGER.error(ex + " " + expMsg);
			throw new CreditCardDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = true)
	public BigInteger getMaxCreditCardNum() throws CreditCardDBAccessException {
		try {
			
		DetachedCriteria criteria = DetachedCriteria.forClass(CreditCard.class);
		criteria.setProjection( Projections.max(CREDIT_CARD_COL_NAME));
		return ((List<BigInteger>)hibernateTemplate.findByCriteria(criteria)).get(0);	
		
		} catch(DataAccessException ex) {
			String expMsg = "Unable to get max credit card number for customer ---->";
			LOGGER.error(ex + " " + expMsg);
			throw new CreditCardDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = false)
	public void save(CreditCard creditCardBean) throws CreditCardDBAccessException {
		try {
			hibernateTemplate.save(creditCardBean);
		} catch(DataAccessException ex) {
			String expMsg = "Unable to save credit card details for ---->" + creditCardBean.toString();
			LOGGER.error(ex + " " + expMsg);
			throw new CreditCardDBAccessException(expMsg);
		}

	}
	
	@Transactional(readOnly = false)
	public void update(CreditCard creditCardBean) throws CreditCardDBAccessException {
		try {
			hibernateTemplate.update(creditCardBean);
		} catch(DataAccessException ex) {
			String expMsg = "Unable to update credit card details for ---->" + creditCardBean.getCreditCardNumber();
			LOGGER.error(ex + " " + expMsg);
			throw new CreditCardDBAccessException(expMsg);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(String creditCardNumber) throws CreditCardDBAccessException {
		try {
		hibernateTemplate.delete(get(creditCardNumber));
		} catch(DataAccessException ex) {
			String expMsg = "Unable to delete credit card details for ---->" + creditCardNumber;
			LOGGER.error(ex + " " + expMsg);
			throw new CreditCardDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = true)
	public List<CreditCard> getAllCreditCards() throws CreditCardDBAccessException {
		try {
		return hibernateTemplate.loadAll(CreditCard.class);
		} catch(DataAccessException ex) {
			String expMsg = "Unable to fetch all credit card details";
			LOGGER.error(ex + " " + expMsg);
			throw new CreditCardDBAccessException(expMsg);
		}
	}
	
	@Transactional(readOnly = true)
    public List<CreditCard> getCreditCardWithParam(BigInteger creditCardNumber, int cvvCode) throws CreditCardDBAccessException {
    	try {
		DetachedCriteria criteria = DetachedCriteria.forClass(CreditCard.class);
		criteria.add(Restrictions.eq(CREDIT_CARD_COL_NAME, creditCardNumber));
		criteria.add(Restrictions.eq("cvvCode", cvvCode));

		return (List<CreditCard>)hibernateTemplate.findByCriteria(criteria);
		
    	} catch(DataAccessException ex) {
    		String expMsg = "Unable to fetch credit card with parameter --->" + creditCardNumber;
			LOGGER.error(ex + " " + expMsg);
			throw new CreditCardDBAccessException(expMsg);
		}
    	
	}

}

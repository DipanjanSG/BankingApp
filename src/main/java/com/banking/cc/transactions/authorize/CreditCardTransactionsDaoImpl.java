package com.banking.cc.transactions.authorize;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on credit_card table
 */
public class CreditCardTransactionsDaoImpl implements CreditCardTransactions{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	private static final  Logger LOGGER = Logger.getLogger(CreditCardTransactionsDaoImpl.class);
    private static final int MINIMUM_CREDIT_CARD_LIST_SIZE = 0;
    
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public CreditCard get(CreditCard creditCardBean) throws DataAccessException {
		return hibernateTemplate.get(CreditCard.class, creditCardBean.creditCardNumber);
	}
	
	@Transactional(readOnly = false)
	public void save(CreditCard creditCardBean) throws DataAccessException {
		hibernateTemplate.save(creditCardBean);

	}
	
	@Transactional(readOnly = false)
	public void update(CreditCard creditCardBean) throws DataAccessException {
		hibernateTemplate.update(creditCardBean);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(CreditCard creditCardBean) throws DataAccessException {
		hibernateTemplate.delete(creditCardBean);
	}
	
	@Transactional(readOnly = false)
	public List<CreditCard> getAllCreditCards() throws DataAccessException {
		return hibernateTemplate.loadAll(CreditCard.class);
	}
	
    public CreditCard getCreditCardWithParam(CreditCard creditCardBean)  throws DataAccessException {
		
    	CreditCard creditCard = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CreditCard.class);
		criteria.add(Restrictions.eq("cvvCode", creditCardBean.cvvCode));
		criteria.add(Restrictions.eq("creditCardNumber", creditCardBean.creditCardNumber));

		List<CreditCard> creditCardList =  (List<CreditCard>)hibernateTemplate.findByCriteria(criteria);
		
		
		if (creditCardList.size() > MINIMUM_CREDIT_CARD_LIST_SIZE ) {
			creditCard = creditCardList.get(MINIMUM_CREDIT_CARD_LIST_SIZE);
		}
		else {
			LOGGER.warn("No credit card was found with number " + creditCardBean.creditCardNumber + " and cvv-code as " + creditCardBean.cvvCode);
		}
        return creditCard;
	}
}

package AuthorizeCCTransactions;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import Logger.AuthorizeCCTransactionsLogs;
import TransactionDetails.Transaction;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on credit_card table
 */
public class CreditCardTransactionsDaoImpl implements CretditCardTransactions{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	final static Logger logger = Logger.getLogger(CreditCardTransactionsDaoImpl.class);

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public CreditCard get(CreditCard creditCardBean) {
		return hibernateTemplate.get(CreditCard.class, creditCardBean.creditCardNumber);
	}
	
	@Transactional(readOnly = false)
	public void save(CreditCard creditCardBean) {
		hibernateTemplate.save(creditCardBean);

	}
	
	@Transactional(readOnly = false)
	public void update(CreditCard creditCardBean) {
		hibernateTemplate.update(creditCardBean);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(CreditCard creditCardBean) {
		hibernateTemplate.delete(creditCardBean);
	}
	
	@Transactional(readOnly = false)
	public List<CreditCard> getAllCreditCards() {
		return hibernateTemplate.loadAll(CreditCard.class);
	}
	
    public CreditCard getCreditCardWithParam(CreditCard creditCardBean)  throws ClassNotFoundException, IOException {
		
    	CreditCard creditCard = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CreditCard.class);
		criteria.add(Restrictions.eq("cvvCode", creditCardBean.cvvCode));
		criteria.add(Restrictions.eq("creditCardNumber", creditCardBean.creditCardNumber));

		List<CreditCard> creditCardList =  (List<CreditCard>)hibernateTemplate.findByCriteria(criteria);
		
		
		if (creditCardList.size() > 0 ) {
			creditCard = creditCardList.get(0);
		}
		else {
			logger.warn("No credit card was found with number " + creditCardBean.creditCardNumber + " and cvv-code as " + creditCardBean.cvvCode);
		}
        return creditCard;
	}
}

package AuthorizeCCTransactions;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import BusinessLogic.SessionFactoryCreation;
import CreateAccount.Customer;

public class CreditCardTransactionsDaoImpl implements CretditCardTransactions{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
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
}

package TransactionDetails;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import BusinessLogic.SessionFactoryCreation;

public class TransactionDaoImpl implements TransactionDao{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = false)
	public Transaction get(Transaction transaction) {
		return hibernateTemplate.get(Transaction.class, transaction.transactionId);
	}

	@Transactional(readOnly = false)
	public void save(Transaction transaction) {
		System.out.println(transaction.toString());
		hibernateTemplate.save(transaction);		
		
	}

	@Transactional(readOnly = false)
	public void update(Transaction transaction) {
		hibernateTemplate.update(transaction);		
		
	}

	@Transactional(readOnly = false)
	public void delete(Transaction transaction) {
		hibernateTemplate.delete(transaction);		
		
	}

	@Transactional(readOnly = false)
	public List<Transaction> getAllTransaction() {
		return hibernateTemplate.loadAll(Transaction.class);
	}

	public List<Transaction> getTransactionDetails(int fromAccount,int toAccount, Timestamp dateFrom , Timestamp dateTo) throws ClassNotFoundException, IOException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class);
		criteria.add(Restrictions.eq("fromAccount", fromAccount));
		criteria.add(Restrictions.eq("toAccount", toAccount));
		criteria.add(Restrictions.gt("dateOfTransaction", dateFrom));
		criteria.add(Restrictions.lt("dateOfTransaction", dateTo));		
        return (List<Transaction>) hibernateTemplate.findByCriteria(criteria);
	}
}

package com.banking.money.transaction;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on transactions table
 */
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
	public Integer save(Transaction transaction) throws DataAccessException , TransactionException{
		return (Integer)hibernateTemplate.save(transaction);		
		
	}

	@Transactional(readOnly = false)
	public void update(Transaction transaction) throws DataAccessException, TransactionException{
		hibernateTemplate.update(transaction);		
		
	}

	@Transactional(readOnly = false)
	public void delete(Transaction transaction) throws DataAccessException, TransactionException{
		hibernateTemplate.delete(transaction);		
		
	}

	@Transactional(readOnly = false)
	public List<Transaction> getAllTransaction() throws DataAccessException, TransactionException {
		return hibernateTemplate.loadAll(Transaction.class);
	}

	public List<Transaction> getTransactionDetails(int fromAccount,int toAccount, Timestamp dateFrom , Timestamp dateTo) throws DataAccessException , TransactionException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class);
		Criterion fromAccountNumber = Restrictions.eq("fromAccount", fromAccount);
		Criterion toAccountNumber = Restrictions.eq("toAccount", toAccount);
		
		LogicalExpression orExp = Restrictions.or(fromAccountNumber, toAccountNumber);
		criteria.add(orExp);
		criteria.add(Restrictions.gt("dateOfTransaction", dateFrom));
		criteria.add(Restrictions.lt("dateOfTransaction", dateTo));
		
        return (List<Transaction>) hibernateTemplate.findByCriteria(criteria);
	}
}

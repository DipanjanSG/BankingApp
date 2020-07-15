package com.banking.money.transaction;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.banking.exceptions.TransactionDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - HibernateTemplate for operations on transactions table
 */
public class TransactionDaoImpl implements TransactionDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	private static final Logger LOGGER = Logger.getLogger(TransactionDaoImpl.class);
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = true)
	public Transaction get(int transactionId) throws TransactionDBAccessException {
		try {
		return hibernateTemplate.get(Transaction.class, transactionId);
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to get Transaction from to Transaction DB";
			LOGGER.error(ex + " " + expMsg);
			throw new TransactionDBAccessException(expMsg);
		}
	}

	@Transactional(readOnly = false)
	public Integer save(Transaction transaction) throws TransactionDBAccessException {
		try {
			return (Integer)hibernateTemplate.save(transaction);		
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to save to Transaction DB";
			LOGGER.error(ex + " " + expMsg);
			throw new TransactionDBAccessException(expMsg);
		}
		
	}

	@Transactional(readOnly = false)
	public void update(Transaction transaction) throws TransactionDBAccessException{
		try {hibernateTemplate.update(transaction);	
	
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to update to Transaction DB";
			LOGGER.error(ex + " " + expMsg);
			throw new TransactionDBAccessException(expMsg);
		}
		
	}

	@Transactional(readOnly = false)
	public void delete(int transactionId) throws TransactionDBAccessException{
		try {hibernateTemplate.delete(get(transactionId));	
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to delete from Transaction DB";
			LOGGER.error(ex + " " + expMsg);
			throw new TransactionDBAccessException(expMsg);
		}
		
	}

	@Transactional(readOnly = true)
	public List<Transaction> getAllTransaction() throws TransactionDBAccessException {
		try {return hibernateTemplate.loadAll(Transaction.class);
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to get all Transactions";
			LOGGER.error(ex + " " + expMsg);
			throw new TransactionDBAccessException(expMsg);
		}
		
	}

	@Transactional(readOnly = true)
	public List<Transaction> getTransactionDetails(int fromAccount,int toAccount, Timestamp dateFrom , Timestamp dateTo) throws TransactionDBAccessException {
		try {
		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class);
		Criterion fromAccountNumber = Restrictions.eq("fromAccount", fromAccount);
		Criterion toAccountNumber = Restrictions.eq("toAccount", toAccount);
		
		LogicalExpression orExp = Restrictions.or(fromAccountNumber, toAccountNumber);
		criteria.add(orExp);
		criteria.add(Restrictions.gt("dateOfTransaction", dateFrom));
		criteria.add(Restrictions.lt("dateOfTransaction", dateTo));
		
        return (List<Transaction>) hibernateTemplate.findByCriteria(criteria);
		} catch (DataAccessException ex ) {
			String expMsg = "Unable to get specific Trasaction Details";
			LOGGER.error(ex + " " + expMsg);
			throw new TransactionDBAccessException(expMsg);
		}
	}
}

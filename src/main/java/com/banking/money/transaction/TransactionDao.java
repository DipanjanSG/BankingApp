package com.banking.money.transaction;

import java.util.List;

import com.banking.exceptions.TransactionDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Interface containing functions for operations on transaction table
 */
public interface TransactionDao {

	Transaction get(Transaction transaction);
	Integer save(Transaction transaction) throws TransactionDBAccessException;
	void update(Transaction transaction) throws TransactionDBAccessException;
	void delete(Transaction transaction) throws TransactionDBAccessException;
	List<Transaction> getAllTransaction() throws TransactionDBAccessException;
}

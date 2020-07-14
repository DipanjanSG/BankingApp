package com.banking.money.transaction;

import java.sql.Timestamp;
import java.util.List;

import com.banking.exceptions.TransactionDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Interface containing functions for operations on transaction table
 */
public interface TransactionDao {

	Transaction get(int transactionId) throws TransactionDBAccessException;
	Integer save(Transaction transaction) throws TransactionDBAccessException;
	void update(Transaction transaction) throws TransactionDBAccessException;
	void delete(int transactionId) throws TransactionDBAccessException;
	List<Transaction> getAllTransaction() throws TransactionDBAccessException;
	List<Transaction> getTransactionDetails(int fromAccount,int toAccount, Timestamp dateFrom , Timestamp dateTo) throws TransactionDBAccessException;
}

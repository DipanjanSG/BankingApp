package com.banking.money.transaction;

import java.util.List;

/**
 * @author Dipanjan Sengupta
 * @purpose - Interface containing functions for operations on transaction table
 */
public interface TransactionDao {

	Transaction get(Transaction transaction);
	Integer save(Transaction transaction);
	void update(Transaction transaction);
	void delete(Transaction transaction);
	List<Transaction> getAllTransaction();
}

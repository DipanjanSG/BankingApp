package com.banking.money.transaction;

import java.util.List;

import com.banking.exceptions.AccountsDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Interface containing functions for operations on accounts table
 */
public interface AccountsDao{
	
	Account get(int accountNumber) throws AccountsDBAccessException;
	void save(Account accountsBean) throws AccountsDBAccessException;
	void update(Account accountsBean) throws AccountsDBAccessException;
	void delete(int accountNumber) throws AccountsDBAccessException;
	List<Account> getAllAccounts() throws AccountsDBAccessException;
	Account getAccountWithCustomerId(int customerId) throws AccountsDBAccessException;
}

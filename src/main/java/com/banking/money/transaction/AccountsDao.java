package com.banking.money.transaction;

import java.util.List;

import com.banking.exceptions.AccountsDBAccessException;

/**
 * @author Dipanjan Sengupta
 * @purpose - Interface containing functions for operations on accounts table
 */
public interface AccountsDao{
	
	Accounts get(Accounts accountsBean) throws AccountsDBAccessException;
	void save(Accounts accountsBean) throws AccountsDBAccessException;
	void update(Accounts accountsBean) throws AccountsDBAccessException;
	void delete(Accounts accountsBean) throws AccountsDBAccessException;
	List<Accounts> getAllAccounts() throws AccountsDBAccessException;
	
}

package com.banking.cc.transactions.authorize;

import java.util.List;

import com.banking.exceptions.CreditCardDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Interface for operations on credit card table
 */
public interface CreditCardTransactions {

	void save(CreditCard creditCard) throws CreditCardDBAccessException;
	void update(CreditCard creditCard) throws CreditCardDBAccessException;
	void delete(CreditCard creditCard) throws CreditCardDBAccessException;
	List<CreditCard> getAllCreditCards() throws CreditCardDBAccessException;
}

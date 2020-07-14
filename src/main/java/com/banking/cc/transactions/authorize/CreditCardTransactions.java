package com.banking.cc.transactions.authorize;

import java.util.List;

import com.banking.account.creation.Customer;
import com.banking.exceptions.CreditCardDBAccessException;
import com.banking.exceptions.CustomerDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Interface for operations on credit card table
 */
public interface CreditCardTransactions {

	CreditCard get(String creditCardNumber) throws CreditCardDBAccessException;
	void save(CreditCard creditCard) throws CreditCardDBAccessException;
	void update(CreditCard creditCard) throws CreditCardDBAccessException;
	void delete(String creditCardNumber) throws CreditCardDBAccessException;
	List<CreditCard> getAllCreditCards() throws CreditCardDBAccessException;
	List<CreditCard> getCreditCardWithParam(String creditCardNumber, String cvvCode) throws CreditCardDBAccessException;
}

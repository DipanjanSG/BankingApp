package com.banking.cc.transactions.authorize;

import java.util.List;

/**
 * @author Dipanjan Sengupta
 * @purpose - Interface for operations on credit card table
 */
public interface CreditCardTransactions {

	void save(CreditCard creditCard);
	void update(CreditCard creditCard);
	void delete(CreditCard creditCard);
	List<CreditCard> getAllCreditCards();
}

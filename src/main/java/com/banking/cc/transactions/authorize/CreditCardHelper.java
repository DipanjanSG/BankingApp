package com.banking.cc.transactions.authorize;

import org.springframework.dao.DataAccessException;

import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - This class helps in updating the value in the credit card amount in the table credit_card after
 * 			  the transaction has been authorized
 */
public class CreditCardHelper {

	public void creditCardAmountBorrowedUpdation(CreditCard retievedCreditCardBean , double amount ) throws DataAccessException {
		retievedCreditCardBean.setAmount(retievedCreditCardBean.getAmount() + amount);
		CreditCardTransactionsDaoImpl creditCardTransactionsDaoImpl = ContextBeans.getcreateCreditCardTransactionsDao();
		creditCardTransactionsDaoImpl.update(retievedCreditCardBean);
	}
}

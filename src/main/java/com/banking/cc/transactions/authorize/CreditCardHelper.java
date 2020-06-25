package com.banking.cc.transactions.authorize;

import com.banking.exceptions.CreditCardDBAccessException;
import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta 
 * @purpose - This class helps in updating the value in the credit card amount in the table credit_card after
 * 			  the transaction has been authorized
 */
public class CreditCardHelper {

	public void creditCardAmountBorrowedUpdation(final CreditCard retievedCreditCardBean , final double amount ) throws CreditCardDBAccessException {
		retievedCreditCardBean.setAmount(retievedCreditCardBean.getAmount() + amount);
		CreditCardTransactionsDaoImpl creditCardTransactionsDaoImpl = ContextBeans.getcreateCreditCardTransactionsDao();
		creditCardTransactionsDaoImpl.update(retievedCreditCardBean);
	}
}

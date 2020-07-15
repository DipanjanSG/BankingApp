package com.banking.cc.transactions.authorize;

import com.banking.exceptions.CreditCardDBAccessException;
import com.banking.spring.beans.ContextBeansFactory;

/**
 * @author Dipanjan Sengupta 
 * @purpose - This class helps in updating the value in the credit card amount in the table credit_card after
 * 			  the transaction has been authorized
 */
public class CreditCardHelper {

	/**
	 * @purpose - Update the amount borrowed in the credit card table
	 * @param - 
	 *         retievedCreditCardBean : Object containing CC details
	 *         amount : amount borrowed
	 */
	public void creditCardAmountBorrowedUpdation(final CreditCard retievedCreditCardBean , final double amount ) throws CreditCardDBAccessException {
		retievedCreditCardBean.setAmount(retievedCreditCardBean.getAmount() + amount);
		CreditCardTransactionsDaoImpl creditCardTransactionsDaoImpl = ContextBeansFactory.getCreateCreditCardTransactionsDao();
		creditCardTransactionsDaoImpl.update(retievedCreditCardBean);
	}
}

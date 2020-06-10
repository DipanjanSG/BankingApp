package AuthorizeCCTransactions;

import configs.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - This class helps in updating the value in the credit card amount in the table credit_card after
 * 			  the transaction has been authorized
 */
public class CreditCardHelper {

	public void creditCardAmountBorrowedUpdation(CreditCard retievedCreditCardBean , double amount ) throws ClassNotFoundException {
		retievedCreditCardBean.setAmount(retievedCreditCardBean.getAmount() + amount);
		CreditCardTransactionsDaoImpl CreditCardTransactionsDaoImpl = ContextBeans.getcreateCreditCardTransactionsDao();
		CreditCardTransactionsDaoImpl.update(retievedCreditCardBean);
	}
}

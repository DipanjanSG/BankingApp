package AuthorizeCCTransactions;

import configs.ContextBeans;

public class CreditCardHelper {

	public void creditCardAmountBorrowedUpdation(CreditCard retievedCreditCardBean , double amount ) throws ClassNotFoundException {
		retievedCreditCardBean.setAmount(retievedCreditCardBean.getAmount() + amount);
		//CreditCardTransactionsDao creditCardTransactionsDao = new CreditCardTransactionsDao();
		CreditCardTransactionsDaoImpl CreditCardTransactionsDaoImpl = ContextBeans.getcreateCreditCardTransactionsDao();
		CreditCardTransactionsDaoImpl.update(retievedCreditCardBean);
	}
}

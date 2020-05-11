package AuthorizeCCTransactions;

public class CreditCardHelper {

	public void creditCardAmountBorrowedUpdation(CreditCardBean retievedCreditCardBean , double amount ) throws ClassNotFoundException {
		retievedCreditCardBean.setAmount(retievedCreditCardBean.getAmount() + amount);
		CreditCardTransactionsDao creditCardTransactionsDao = new CreditCardTransactionsDao();
		creditCardTransactionsDao.update(retievedCreditCardBean);
	}
}

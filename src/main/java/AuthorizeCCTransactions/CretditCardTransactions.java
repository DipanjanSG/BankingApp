package AuthorizeCCTransactions;

import java.util.List;

public interface CretditCardTransactions {

	void save(CreditCard creditCard);
	void update(CreditCard creditCard);
	void delete(CreditCard creditCard);
	List<CreditCard> getAllCreditCards();
}

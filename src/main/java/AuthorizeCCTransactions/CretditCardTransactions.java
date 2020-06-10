package AuthorizeCCTransactions;

import java.util.List;

/**
 * @author Dipanjan Sengupta
 * @purpose - Interface for operations on credit card table
 */
public interface CretditCardTransactions {

	void save(CreditCard creditCard);
	void update(CreditCard creditCard);
	void delete(CreditCard creditCard);
	List<CreditCard> getAllCreditCards();
}

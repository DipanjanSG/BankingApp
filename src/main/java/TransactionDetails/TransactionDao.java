package TransactionDetails;

import java.util.List;

public interface TransactionDao {

	Transaction get(Transaction transaction);
	void save(Transaction transaction);
	void update(Transaction transaction);
	void delete(Transaction transaction);
	List<Transaction> getAllTransaction();
}

package Transactions;

import java.util.List;

/**
 * @author Dipanjan Sengupta
 * @purpose - Interface containing functions for operations on accounts table
 */
public interface AccountsDao{
	
	Accounts get(Accounts accountsBean);
	void save(Accounts accountsBean);
	void update(Accounts accountsBean);
	void delete(Accounts accountsBean);
	List<Accounts> getAllAccounts();
	
}

package Transactions;

import java.util.List;

public interface AccountsDao{
	
	Accounts get(Accounts accountsBean);
	void save(Accounts accountsBean);
	void update(Accounts accountsBean);
	void delete(Accounts accountsBean);
	List<Accounts> getAllAccounts();
	
}

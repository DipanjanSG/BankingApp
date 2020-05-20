package CreateAccount;

import java.util.List;

public interface CustomerDao{
	
	void save(Customer customer);
	void update(Customer customer);
	void delete(Customer customer);
	List<Customer> getCustomers();
}

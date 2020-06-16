package com.banking.account.creation;

import java.util.List;

/**
 * @author Dipanjan Sengupta
 * @purpose - This interface represents the methods for operations on customer table
 */
public interface CustomerDao{
	
	Integer save(Customer customer);
	void update(Customer customer);
	void delete(Customer customer);
	List<Customer> getCustomers();
}

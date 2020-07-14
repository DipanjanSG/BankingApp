package com.banking.account.creation;

import java.util.List;

import com.banking.exceptions.CustomerDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - This interface represents the methods for operations on customer table
 */
public interface CustomerDao {
	
	Integer save(Customer customer) throws CustomerDBAccessException;
	void update(Customer customer) throws CustomerDBAccessException;
	void delete(int customerId) throws CustomerDBAccessException;
	List<Customer> getCustomers() throws CustomerDBAccessException;
	Customer get(int customerId) throws CustomerDBAccessException;
	List<Customer> getCustomerWithParam(String userName, String emailId) throws CustomerDBAccessException;

}

package com.banking.account.creation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.banking.exceptions.CustomerDBAccessException;
import com.banking.spring.beans.ContextBeans;

public class CustomerHelper {
	
	public Set<String> checkIfDetailsAlreadyPresent(final Customer customer) throws CustomerDBAccessException {
		Set<String> existingFields = new HashSet<String>(); 
		CustomerDaoImpl createAccountDaoImpl =  ContextBeans.getCreateAccountDao();
    	List <Customer> customers = createAccountDaoImpl.getCustomers();
    	
    	for (Customer registeredCustomer : customers) {
    		if (registeredCustomer.getUserName().equalsIgnoreCase(customer.getUserName())) {
    			existingFields.add("Name"); 
    		}
    		if (registeredCustomer.getEmailId().equalsIgnoreCase(customer.getEmailId())) {
    			existingFields.add("Email Id");
    		}
    	}
		
		return existingFields;
	}
}

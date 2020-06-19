package com.banking.account.creation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.banking.spring.beans.ContextBeans;

public class CustomerHelper {
	
	public Set<String> checkIfDetailsAlreadyPresent(Customer customer) {
		Set<String> existingFields = new HashSet<String>(); 
		CustomerDaoImpl createAccountDaoImpl =  ContextBeans.getCreateAccountDao();
    	List <Customer> customers = createAccountDaoImpl.getCustomers();
    	
    	for (Customer registeredCustomer : customers) {
    		if (registeredCustomer.userName.equalsIgnoreCase(customer.userName)) {
    			existingFields.add("Name"); 
    		}
    		if (registeredCustomer.emailId.equalsIgnoreCase(customer.emailId)) {
    			existingFields.add("Email Id");
    		}
    	}
		
		return existingFields;
	}
}

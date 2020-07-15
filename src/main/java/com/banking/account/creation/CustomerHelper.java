package com.banking.account.creation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.banking.exceptions.CustomerDBAccessException;
import com.banking.spring.beans.ContextBeansFactory;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Customer Helper class for checking user name and password already present
 */
public class CustomerHelper {
	
	/**
	 * @purpose - Used during customer creation, to find if "Name" and "Email-id" already present
	 */
	public Set<String> checkIfDetailsAlreadyPresent(final Customer customer) throws CustomerDBAccessException {
		Set<String> existingFields = new HashSet<String>(); 
		CustomerDaoImpl createAccountDaoImpl =  ContextBeansFactory.getCreateAccountDao();
    	List <Customer> customers = createAccountDaoImpl.getCustomerWithParam(customer.getUserName(), customer.getEmailId());
    	
    	for (Customer registeredCustomer : customers) {
    		if ((registeredCustomer.getUserName().equalsIgnoreCase(customer.getUserName()))) {
    			existingFields.add("Name"); 
    		}
    		if (registeredCustomer.getEmailId().equalsIgnoreCase(customer.getEmailId())) {
    			existingFields.add("Email Id");
    		}
    	}
		
		return existingFields;
	}
}

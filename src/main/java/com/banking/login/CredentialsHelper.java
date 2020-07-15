package com.banking.login;

import java.util.List;
import com.banking.exceptions.CredentialsDBAccessException;
import com.banking.spring.beans.ContextBeansFactory;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Contains Helper functions for operations on credential table
 */
public class CredentialsHelper {

	private static final int MINIMUM_CUSTOMER_ID = 0;
	
	/** 
	 * @purpose - Validates credentials during Login
	 */
	public int validateCredentials(final Credentials credentials) throws CredentialsDBAccessException {
		    CredentialsDaoImpl credentialsDaoImpl = ContextBeansFactory.getCredentialsDaoImpl();
		    List<Credentials> retrievedCredentialsList = credentialsDaoImpl.getCredentialDetails(credentials.getUserName() ,credentials.getPassword());
		    if ( !retrievedCredentialsList.isEmpty() && retrievedCredentialsList.get(MINIMUM_CUSTOMER_ID).getCustomerBean().getCustomerId() > MINIMUM_CUSTOMER_ID ) {
				return retrievedCredentialsList.get(MINIMUM_CUSTOMER_ID).getCustomerBean().getCustomerId();
			}		    
	        return -1;
	}
}

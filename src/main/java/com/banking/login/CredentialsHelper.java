package com.banking.login;

import java.util.List;
import com.banking.exceptions.CredentialsDBAccessException;
import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Contains Helper functions for operations on credential table
 */
public class CredentialsHelper {

	private static final int MINIMUM_CUSTOMER_ID = 0;
	
	public int validateCredentials(final Credentials credentials) throws CredentialsDBAccessException {
		
		    CredentialsDaoImpl credentialsDaoImpl = ContextBeans.getCredentialsDaoImpl();
		    
		    List<Credentials> retrievedCredentialsList = credentialsDaoImpl.getCredentialDetails(credentials);

		    if ( !retrievedCredentialsList.isEmpty() && retrievedCredentialsList.get(MINIMUM_CUSTOMER_ID).getCustomerId() > MINIMUM_CUSTOMER_ID ) {
				return retrievedCredentialsList.get(MINIMUM_CUSTOMER_ID).getCustomerId();
			}		    
			
	        return 0;
	}
}

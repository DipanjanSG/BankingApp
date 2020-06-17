package com.banking.login;

import org.springframework.dao.DataAccessException;
import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Contains Helper functions for operations on credential table
 */
public class CredentialsHelper {

	private static final int MINIMUM_CUSTOMER_ID = 0;
	
	public int validateCredentials(Credentials credentials) throws DataAccessException {
		
		    CredentialsDaoImpl credentialsDaoImpl = ContextBeans.getCredentialsDaoImpl();
		    credentials = credentialsDaoImpl.getCredentialDetails(credentials);
		    
		    if ( credentials!= null && credentials.customerId > MINIMUM_CUSTOMER_ID ) {
				return credentials.getCustomerId();
			}		    
			
	        return 0;
	}
}

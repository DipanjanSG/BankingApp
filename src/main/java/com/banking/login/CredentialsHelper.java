package com.banking.login;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.banking.features.PerformTransactions;
import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Contains Helper functions for operations on credential table
 */
public class CredentialsHelper {

	private static final int MINIMUM_CUSTOMER_ID = 0;
	
	public int validateCredentials(Credentials credentials) throws ClassNotFoundException, IOException {
		
		    CredentialsDaoImpl credentialsDaoImpl = ContextBeans.getCredentialsDaoImpl();
		    credentials = credentialsDaoImpl.getCredentialDetails(credentials);
		    
		    if ( credentials!= null && credentials.customerId > MINIMUM_CUSTOMER_ID ) {
				return credentials.getCustomerId();
			}		    
			
	        return 0;
	}
}

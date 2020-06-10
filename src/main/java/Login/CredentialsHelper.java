package Login;

import java.io.IOException;

import org.apache.log4j.Logger;

import CustomerActivities.PerformTransactions;
import configs.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Contains Helper functions for operations on credential table
 */
public class CredentialsHelper {

	public int validateCredentials(Credentials credentials) throws ClassNotFoundException, IOException {
		
		    CredentialsDaoImpl credentialsDaoImpl = ContextBeans.getCredentialsDaoImpl();
		    credentials = credentialsDaoImpl.getCredentialDetails(credentials);
		    
		    if ( credentials!= null && credentials.customerId > 0 ) {
				return credentials.getCustomerId();
			}		    
			
	        return 0;
	}
}

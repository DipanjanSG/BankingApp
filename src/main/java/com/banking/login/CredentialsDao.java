package com.banking.login;

import java.util.List;

import com.banking.account.creation.Customer;
import com.banking.exceptions.CredentialsDBAccessException;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Interface with functions for operations on credentials table 
 */
public interface CredentialsDao {
		
	    Credentials get(int customerId) throws CredentialsDBAccessException;
		void save(Credentials customer) throws CredentialsDBAccessException;
		void update(Credentials customer) throws CredentialsDBAccessException;
		void delete(int customerId) throws CredentialsDBAccessException;
		List<Credentials> getCredentials() throws CredentialsDBAccessException;
		List<Credentials> getCredentialDetails( String userName, String password ) throws CredentialsDBAccessException;
	}

package com.banking.login;

import java.util.List;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Interface with functions for operations on credentials table 
 */
public interface CredentialsDao {
		
		void save(Credentials customer);
		void update(Credentials customer);
		void delete(Credentials customer);
		List<Credentials> getCustomers();
	}

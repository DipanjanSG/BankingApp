package com.banking.account.creation;

import java.util.Date;

import java.util.Set;
import com.banking.cc.transactions.authorize.CreditCard;
import com.banking.login.Credentials;
import com.banking.money.transaction.Account;
import lombok.ToString;

/**
 * @author Dipanjan Sengupta  
 * @purpose - This POJO represents the Customer Object and is mapped to customer class
 */

@ToString

public class Customer {
	
	private int customerId;
	private String userName ;
	private Date dateOfBirth;
	private String address;
	private String emailId;
	private Set <Account> allAccountsHeld;
	CreditCard creditCard;
	Credentials credentials;
	
    public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public Customer() {
		
	}
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Set<Account> getAllAccountsHeld() {
		return allAccountsHeld;
	}

	public void setAllAccountsHeld(Set<Account> allAccountsHeld) {
		this.allAccountsHeld = allAccountsHeld;
	}

	public Customer(String userName, Date dateOfBirth, String address, String emailId,
			Set <Account> allAccountsHeld,  CreditCard creditCard, Credentials credentials) {
		super();
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.emailId = emailId;
		this.allAccountsHeld = allAccountsHeld;
		this.creditCard = creditCard;
		this.credentials = credentials;
	}

	public Customer(String userName, Date dateOfBirth, String address, String emailId) {
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.emailId = emailId;
	}
	
	public Customer(int customerId) {
		this.customerId = customerId;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dob) {
		this.dateOfBirth = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}

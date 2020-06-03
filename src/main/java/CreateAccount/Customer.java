package CreateAccount;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import AuthorizeCCTransactions.CreditCard;
import Login.Credentials;
import Transactions.Accounts;

public class Customer {
	
	int customerId;
	String userName ;
	Date dateOfBirth;
	String address;
	String emailId;
	Set <Accounts> allAccountsHeld;
	
	@Autowired
	Credentials credentials;
	
	@Autowired
	CreditCard creditCardBean;
	
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Set<Accounts> getAllAccountsHeld() {
		return allAccountsHeld;
	}

	public void setAllAccountsHeld(Set<Accounts> allAccountsHeld) {
		this.allAccountsHeld = allAccountsHeld;
	}

	public Customer(String userName, Date dateOfBirth, String address, String emailId,
			Set <Accounts> allAccountsHeld, Credentials credentials) {
		super();
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.emailId = emailId;
		this.allAccountsHeld = allAccountsHeld;
		this.credentials = credentials;
	}

	public Customer(String userName, Date dateOfBirth, String address, String emailId) {
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.emailId = emailId;
	}
	
	public Customer() {
		
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

	public CreditCard getCreditCardBean() {
		return creditCardBean;
	}

	public void setCreditCardBean(CreditCard creditCardBean) {
		this.creditCardBean = creditCardBean;
	}
}

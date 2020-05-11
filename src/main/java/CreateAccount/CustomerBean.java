package CreateAccount;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import AuthorizeCCTransactions.CreditCardBean;
import Login.Credentials;
import Transactions.AccountsBean;

public class CustomerBean {
	
	int customerId;
	String userName ;
	Date dateOfBirth;
	String address;
	String emailId;
	Set <AccountsBean> allAccountsHeld;
	
	@Autowired
	Credentials credentials;
	
	@Autowired
	CreditCardBean creditCardBean;
	
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Set<AccountsBean> getAllAccountsHeld() {
		return allAccountsHeld;
	}

	public void setAllAccountsHeld(Set<AccountsBean> allAccountsHeld) {
		this.allAccountsHeld = allAccountsHeld;
	}

	public CustomerBean(String userName, Date dateOfBirth, String address, String emailId,
			Set <AccountsBean> allAccountsHeld, Credentials credentials) {
		super();
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.emailId = emailId;
		this.allAccountsHeld = allAccountsHeld;
		this.credentials = credentials;
	}

	public CustomerBean(String userName, Date dateOfBirth, String address, String emailId) {
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.emailId = emailId;
	}
	
	public CustomerBean() {
		
	}
	public CustomerBean(int customerId) {
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

	public CreditCardBean getCreditCardBean() {
		return creditCardBean;
	}

	public void setCreditCardBean(CreditCardBean creditCardBean) {
		this.creditCardBean = creditCardBean;
	}
}

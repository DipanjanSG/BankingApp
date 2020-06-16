package com.banking.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.banking.account.creation.Customer;

import lombok.ToString;

/**
 * @author Dipanjan Sengupta
 * @purpose - POJO representing values in credentials table
 */
@ToString
public class Credentials {
	
	String userName;
	String password;
	int customerId;
	private static final String PASSWORD_SUFFIX = "123";
	
	@Autowired
	Customer customerBean;
	
	public Credentials() {
		
	}
	public Credentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomerBean() {
		return customerBean;
	}
	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}
	
	public void createUserIdAndPassword(String fullName) {
		setUserName(fullName.toLowerCase().split(" ")[0]);
		createPassword();
	}
	public void createPassword() {
		setPassword(this.userName + PASSWORD_SUFFIX);
	}	
}

package com.banking.login;

import com.banking.account.creation.Customer;
import lombok.ToString;

/**
 * @author Dipanjan Sengupta 
 * @purpose - POJO representing values in credentials table
 */

@ToString
public class Credentials {
	
	private String userName;
	private String password;
	private int id;
	private static final String PASSWORD_SUFFIX = "123";
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomerBean() {
		return customerBean;
	}
	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}
	
	public void createUserIdAndPassword(String fullName) {
		String[] firstLastName = fullName.toLowerCase().split(" ");
		setUserName(firstLastName[0]+ "_" + firstLastName[1]);
		createPassword();
	}
	public void createPassword() {
		setPassword(this.userName + PASSWORD_SUFFIX);
	}
		
}

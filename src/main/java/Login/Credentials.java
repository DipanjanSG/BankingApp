package Login;

import org.springframework.beans.factory.annotation.Autowired;

import CreateAccount.CustomerBean;

public class Credentials {
	
	String userName;
	String password;
	int customerId;
	
	@Autowired
	CustomerBean customerBean;
	
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
	public CustomerBean getCustomerBean() {
		return customerBean;
	}
	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}
	
	public void createUserIdAndPassword(String fullName) {
		setUserName(fullName.toLowerCase().split(" ")[0]);
		createPassword();
	}
	public void createPassword() {
		setPassword(this.userName+"123");
	}	
}

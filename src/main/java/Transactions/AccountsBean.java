package Transactions;

import org.springframework.beans.factory.annotation.Autowired;

import CreateAccount.CustomerBean;

public class AccountsBean {
	
	int accountNumber;
	double accountBalance;
	
	@Autowired
	CustomerBean customerBean;
	
	String bankAccountType;
	
	public AccountsBean() {
	}
	
	public AccountsBean( double accountBalance, String bankAccountType) {
		this.accountBalance = accountBalance;
		this.bankAccountType = bankAccountType;
	}

	public AccountsBean(int accountNumber, double accountBalance) {
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	
	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}
	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public void incrementAccountBalance(double creditedAmount) {
		this.accountBalance += creditedAmount;
	}
	
	public void decrementAccountBalance(double debitedAmount) {
		this.accountBalance -= debitedAmount;
	}

}

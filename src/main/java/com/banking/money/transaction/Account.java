package com.banking.money.transaction;

import com.banking.account.creation.Customer;
import lombok.ToString;

/**
 * @author Dipanjan Sengupta 
 * @purpose - PoJo representing Accounts table
 */
@ToString
public class Account {
	
	private int accountNumber;	
	private double accountBalance;	
	private Customer customerBean;
	private String bankAccountType;
	
	public Account() {
	}
	
	public Account( double accountBalance, String bankAccountType) {
		this.accountBalance = accountBalance;
		this.bankAccountType = bankAccountType;
	}

	public Account(int accountNumber, double accountBalance) {
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	
	public Customer getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(Customer customerBean) {
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

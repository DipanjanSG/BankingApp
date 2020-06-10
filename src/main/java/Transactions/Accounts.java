package Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import CreateAccount.Customer;
import lombok.ToString;

/**
 * @author Dipanjan Sengupta
 * @purpose - PoJo representing Accounts table
 */
@ToString
public class Accounts {
	
	int accountNumber;
	double accountBalance;
	
	@Autowired
	Customer customerBean;
	
	String bankAccountType;
	
	public Accounts() {
	}
	
	public Accounts( double accountBalance, String bankAccountType) {
		this.accountBalance = accountBalance;
		this.bankAccountType = bankAccountType;
	}

	public Accounts(int accountNumber, double accountBalance) {
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

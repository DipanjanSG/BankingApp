package com.banking.money.transaction;

import java.sql.Timestamp;
import lombok.ToString;

/**
 * @author Dipanjan Sengupta 
 * @purpose - PoJo for representing transaction table
 */

@ToString
public class Transaction {
	
	private int transactionId; 
	private int fromAccount;
	private int toAccount;
	private String transactionType;
	private Timestamp dateOfTransaction;
	private String description;
	private String checqueNumber;
	private double amount;
	private double fromAccountAmt;
	private double toAccountAmt;
	
	public Transaction( int fromAccount, int toAccount, String transactionType,
			 double amount, double fromAccountAmt, double toAccountAmt) {

		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionType = transactionType;
		this.amount = amount;
		this.fromAccountAmt = fromAccountAmt;
		this.toAccountAmt = toAccountAmt;
	}
	
	public Transaction() {
		super();
	}

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}
	public int getToAccount() {
		return toAccount;
	}
	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Timestamp getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(Timestamp dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChecqueNumber() {
		return checqueNumber;
	}
	public void setChecqueNumber(String checqueNumber) {
		this.checqueNumber = checqueNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFromAccountAmt() {
		return fromAccountAmt;
	}

	public void setFromAccountAmt(double fromAccountAmt) {
		this.fromAccountAmt = fromAccountAmt;
	}

	public double getToAccountAmt() {
		return toAccountAmt;
	}

	public void setToAccountAmt(double toAccountAmt) {
		this.toAccountAmt = toAccountAmt;
	}
	
}

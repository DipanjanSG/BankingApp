package com.banking.cc.transactions.authorize;

import lombok.ToString;
/**
 * @author Dipanjan Sengupta 
 * @purpose - This POJO represents the CreditCard Object and is mapped to credit_card class
 */
@ToString
public class CreditCard {
	
	private String creditCardNumber;
	private String cvvCode;
	private int cardOwner;
	private double amount;
	
	public CreditCard(String creditCardNumber, String cvvCode, int cardOwner, double amount) {
		this.creditCardNumber = creditCardNumber;
		this.cvvCode = cvvCode;
		this.cardOwner = cardOwner;
		this.amount = amount;
	}
	
	public CreditCard(String creditCardNumber, String cvvCode, double amount) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.cvvCode = cvvCode;
		this.amount = amount;
	}
	
	public CreditCard() {
		
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	public int getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(int cardOwner) {
		this.cardOwner = cardOwner;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "CreditCard [creditCardNumber=" + creditCardNumber + ", cvvCode=" + cvvCode + ", cardOwner=" + cardOwner
				+ ", amount=" + amount + "]";
	}
	
}

package com.banking.cc.transactions.authorize;

import java.math.BigInteger;
import java.util.Random;

import com.banking.account.creation.Customer;
import com.banking.exceptions.CreditCardDBAccessException;
import com.banking.spring.beans.ContextBeansFactory;

import lombok.ToString;

/**
 * @author Dipanjan Sengupta 
 * @purpose - This POJO represents the CreditCard Object and is mapped to credit_card class
 */

@ToString
public class CreditCard {
	
	private int id;
	private BigInteger creditCardNumber;
	private int cvvCode;
	private double amount;
	
	Customer customerBean;
	
	private static final String CC_NUMBER_INCREMENT_VALUE = "1";
	private Random rand = new Random(); 
	
	public CreditCard(BigInteger creditCardNumber, int cvvCode, double amount) {
		this.creditCardNumber = creditCardNumber;
		this.cvvCode = cvvCode;
		this.amount = amount;
	}
	
	public CreditCard() {
		 
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(BigInteger creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public int getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(int cvvCode) {
		this.cvvCode = cvvCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customer getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}
	
	public void generateNewCreditCardValues() throws CreditCardDBAccessException {
		 cvvCode = rand.nextInt(999);
		 if (cvvCode < 100) {
			 cvvCode += 100;
		 }
		 
		 CreditCardTransactionsDaoImpl creditCardTransactionsDaoImpl = ContextBeansFactory.getCreateCreditCardTransactionsDao();
		 creditCardNumber = creditCardTransactionsDaoImpl.getMaxCreditCardNum().add(new BigInteger(CC_NUMBER_INCREMENT_VALUE));
	}
	
}

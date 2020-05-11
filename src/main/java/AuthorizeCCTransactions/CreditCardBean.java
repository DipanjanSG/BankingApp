package AuthorizeCCTransactions;

import org.springframework.context.annotation.ComponentScan;


import lombok.ToString;

@ToString
public class CreditCardBean {
	
	String creditCardNumber;
	String cvvCode;
	int cardOwner;
	double amount;
	
	public CreditCardBean(String creditCardNumber, String cvvCode, int cardOwner, double amount) {
		this.creditCardNumber = creditCardNumber;
		this.cvvCode = cvvCode;
		this.cardOwner = cardOwner;
		this.amount = amount;
	}
	
	public CreditCardBean(String creditCardNumber, String cvvCode, double amount) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.cvvCode = cvvCode;
		this.amount = amount;
	}
	
	public CreditCardBean() {
		
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
	
	
}

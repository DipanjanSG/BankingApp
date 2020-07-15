package com.banking.constants;

/**
 * @author Dipanjan Sengupta 
 * @purpose - This class represents constants to be used in this Banking Application
 */

public class Constants {
	
	public enum TransactionStatus {
		OK ("OK"), INVALID_ACCOUNT ("INVALID ACCOUNT NUMBER"), INSUFFICIENT_AMOUNT_ENTERED("INSUFFICIENT AMOUNT"), 
		TRANSACTION_FAILED("TRANSACTION FAILED");
		
		 private String status;

		 TransactionStatus(String status) {
		        this.status = status;
		    }

		    public String getStatus() {
		        return status;
		    }
	}
	
	public static final String UNREACHABLE_SERVER = "Bank Unreachable , check Internet Connection/try later";
	public static final String ACCOUNT_CREATION_SUCCESSFUL = "Account created successfully with Account Number";
	public static final String ACCOUNT_CREATION_UNSUCCESSFUL = "Account has not been created for -";
	public static final String ACCOUNT_CREATION_VALUES = "Values entered against fields - ";
	public static final String ACCOUNT_CREATION_INVALID_VALUES = " , ALREADY EXISTS. Enter different values";
	public static final String ACCOUNT_CREATION_INFO_MISSING ="All Information not entered";
	
	public static final String CC_INVAID_AMT = "Invalid Amount Entered";
	public static final String CC_INVALID_DETAILS = "Invalid Credit Card Details";
	public static final String CC_TRANSACTION_OK = "Transaction Successful";
	
	public static final String TRANSACTIONS_NOT_FOUND =  "No transactions found for the given date range";
	
	public static final String LOGIN_INVALID_CREDENTIALS =  "Invalid Credentials";
	
	public static final String FINANCIAL_TRANSACTION_OK =  CC_TRANSACTION_OK;
	
}

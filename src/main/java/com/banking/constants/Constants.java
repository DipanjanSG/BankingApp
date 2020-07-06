package com.banking.constants;

public class Constants {
	
	public static enum TransactionStatus {
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
	
}

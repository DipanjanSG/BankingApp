package TransactionDetails;

import java.sql.Timestamp;

public class TransactionDetailsBean {
	
	int transactionId; 
	int fromAccount;
	int toAccount;
	String transactionType;
	Timestamp dateOfTransaction;
	String description;
	String checqueNumber;
	double amount;
	double fromAccountAmt;
	double toAccountAmt;
	
	
	
	public TransactionDetailsBean(int transactionId, int fromAccount, int toAccount, String transactionType,
			Timestamp dateOfTransaction, String description, String checqueNumber, double amount, double fromAccountAmt,
			double toAccountAmt) {
		super();
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionType = transactionType;
		this.dateOfTransaction = dateOfTransaction;
		this.description = description;
		this.checqueNumber = checqueNumber;
		this.amount = amount;
		this.fromAccountAmt = fromAccountAmt;
		this.toAccountAmt = toAccountAmt;
	}

	public TransactionDetailsBean( int fromAccount, int toAccount, String transactionType,
			 double amount, double fromAccountAmt, double toAccountAmt) {

		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionType = transactionType;
		this.amount = amount;
		this.fromAccountAmt = fromAccountAmt;
		this.toAccountAmt = toAccountAmt;
	}
	
	public TransactionDetailsBean() {
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

	@Override
	public String toString() {
		return "TransactionDetailsBean [transactionId=" + transactionId + ", fromAccount=" + fromAccount
				+ ", toAccount=" + toAccount + ", transactionType=" + transactionType + ", dateOfTransaction="
				+ dateOfTransaction + ", description=" + description + ", checqueNumber=" + checqueNumber + ", amount="
				+ amount + ", fromAccountAmt=" + fromAccountAmt + ", toAccountAmt=" + toAccountAmt + "]";
	}
	

	
}

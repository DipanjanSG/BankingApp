package Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

public class AuthorizeCCTransactionsLogs {

	public void beforeApprovingCCTransactions() {
		System.out.println("Going to begin transaction for Credit Card");
	}
	
	public void afterApprovingCCTransactions() {
		System.out.println("Going to end transaction for Credit Card");
	}
}

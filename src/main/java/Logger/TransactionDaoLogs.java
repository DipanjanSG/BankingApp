package Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

public class TransactionDaoLogs {

	public void beforePerformTransaction() {
		System.out.println("Starting Amount Transfer");
	}
	
	public void afterPerformTransaction() {
		System.out.println("Amount Transfer completed");
	}
}

package Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

public class CreateAccountLogs {

	public void beforeCreateNewAccount() {
		System.out.println("Go into create a new account");
	}
	
	public void afterCreateNewAccount() {
		System.out.println("Have exited account Creation Utility");
	}
}

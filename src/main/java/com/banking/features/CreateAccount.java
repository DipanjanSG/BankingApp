package com.banking.features;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.banking.account.creation.Customer;
import com.banking.account.creation.CustomerDaoImpl;
import com.banking.account.creation.CustomerHelper;
import com.banking.cc.transactions.authorize.CreditCard;
import com.banking.exceptions.AccountCreationException;
import com.banking.exceptions.AccountsDBAccessException;
import com.banking.exceptions.CreditCardDBAccessException;
import com.banking.exceptions.CustomerDBAccessException;
import com.banking.login.Credentials;
import com.banking.spring.beans.ContextBeansFactory;
import com.banking.money.transaction.Account;
import com.banking.money.transaction.AccountsDaoImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Servlet for creating a new account
 */
@WebServlet("/createAccountServlet")
public class CreateAccount extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(CreateAccount.class);
	private static final  double MINIMUM_STARTING_BALANCE = 0.0;
	private static final String EMAIL_ID_AT_THE_RATE = "@";
	private static final String ALL_DETAILS_NOT_ENTERED = "allDetailsNotEntered";
	HttpServletRequest tempRequest;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		tempRequest= request;
		String userName = request.getParameter("uName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = null;
		
		
			dateOfBirth = formatter.parse(request.getParameter("dateOfBirth"));
			String address = request.getParameter("address");
			String emailId = request.getParameter("emailId");
			String bankAccountType = request.getParameter("bankAccountType");
			
			Customer customerBean = new Customer();
			customerBean.setUserName(userName);
			customerBean.setDateOfBirth(dateOfBirth);
			customerBean.setAddress(address);
			customerBean.setEmailId(emailId);
		
		if (userName.length() > 0 && dateOfBirth.toString().length() > 0 && address.length() > 0 && emailId.length() > 0 && emailId.contains(EMAIL_ID_AT_THE_RATE) ) {
			CustomerHelper customerHelper = new CustomerHelper();  
			Set<String> matchedFields = customerHelper.checkIfDetailsAlreadyPresent(customerBean);
			if (!matchedFields.isEmpty()) {
				request.setAttribute("existingFields", matchedFields.toString().substring(1, matchedFields.toString().length() - 1));
				RequestDispatcher rd = request.getRequestDispatcher("createAccount.jsp");
				rd.forward(request, response);
			}
			
			generateNewCustomerAndAcc(bankAccountType, customerBean);
			
		} else {
			request.setAttribute(ALL_DETAILS_NOT_ENTERED, true);
			LOGGER.error("All values for creating account not entered - ");
			throw new AccountCreationException("All values for creating account not entered " + customerBean.toString() );
			}
		} catch (ParseException ex) {
			LOGGER.error(ex);
			request.setAttribute(ALL_DETAILS_NOT_ENTERED, true);
		  } catch (CustomerDBAccessException ex) {
			LOGGER.error(ex);
		  }catch (AccountCreationException ex) {
			LOGGER.error(ex);
		  } catch (CreditCardDBAccessException ex) {
			LOGGER.error(ex);
		  }catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(ALL_DETAILS_NOT_ENTERED, true);
			LOGGER.error(e);
		}
	try {	
		request = tempRequest;
		RequestDispatcher rd = request.getRequestDispatcher("createAccount.jsp");
		rd.forward(request, response);
	} catch (ServletException e) {
		LOGGER.error(e);
	}catch (IOException e) {
		LOGGER.error(e);
	}
	}
	

    /**
     * @author Dipanjan Sengupta 
     * @purpose - Creates a new customer, a new account, credentials, credit card
     * @param - bankAccountType : "Savings / Current Bank Account"
     *          customerBean: Details of the customer to be created 
     * @throws CreditCardDBAccessException 
     */
    void generateNewCustomerAndAcc(String bankAccountType, Customer customerBean) throws CustomerDBAccessException, AccountsDBAccessException, AccountCreationException, CreditCardDBAccessException  {
    	
		CustomerDaoImpl createAccountDao =  ContextBeansFactory.getCreateAccountDao();
		Account accountsBean = new Account();
		accountsBean.setAccountBalance(MINIMUM_STARTING_BALANCE);
		accountsBean.setBankAccountType(bankAccountType);
		
		Set <Account> allAccountsHeld = new HashSet<Account>();
		allAccountsHeld.add(accountsBean);
		Credentials credentials = new Credentials();
		credentials.createUserIdAndPassword(customerBean.getUserName());
		credentials.setCustomerBean(customerBean);
		customerBean.setAllAccountsHeld(allAccountsHeld);
		customerBean.setCredentials(credentials);
		
		CreditCard creditCard = new CreditCard();
		creditCard.generateNewCreditCardValues();
		creditCard.setCustomerBean(customerBean);
		customerBean.setCreditCard(creditCard);
		
		int customerNumber = createAccountDao.save(customerBean);
		AccountsDaoImpl accountsDaoImplLoggedInUser = ContextBeansFactory.getAcountsDaoImpl();
					
		int generatedAccountNumber = accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerNumber).getAccountNumber();
		
		if (generatedAccountNumber != 0) {
			 LOGGER.info("New Customer record created with account no -" + generatedAccountNumber);
			 tempRequest.setAttribute("accountNumber", generatedAccountNumber);
			 tempRequest.setAttribute("credentials"," Your User name is : \"" + credentials.getUserName() + "\" and your Password is : \"" + credentials.getPassword() + "\"");
			 tempRequest.setAttribute("creditCardDetails", "New Credit Card Number : " + creditCard.getCreditCardNumber() + " , CVV : " + creditCard.getCvvCode());
		} else {

				LOGGER.error("Account has not been created for " + customerBean.getUserName());
				tempRequest.setAttribute("accountNotCreated", true);
				tempRequest.setAttribute("userName", customerBean.getUserName());
				throw new AccountCreationException("Account has not been created for " + customerBean.toString());
		 }

				
    }
}
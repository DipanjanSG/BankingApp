package com.banking.features;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.banking.account.creation.Customer;
import com.banking.account.creation.CustomerDaoImpl;
import com.banking.cc.transactions.authorize.CreditCard;
import com.banking.cc.transactions.authorize.CreditCardHelper;
import com.banking.cc.transactions.authorize.CreditCardTransactionsDaoImpl;
import com.banking.exceptions.CustomerDBAccessException;
import com.banking.exceptions.CreditCardDBAccessException;
import com.banking.exceptions.CreditCardException;
import com.banking.spring.beans.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for Credit Card Transactions
 */

@WebServlet("/AuthorizeCCTransactionsServlet")
public class AuthorizeCCTransactions extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(AuthorizeCCTransactions.class);
	private static final int MAXIMUM_AMOUNT = 100000;
	private static final String INVALID_DETAILS = "invalidDetails";
    private static final int MINIMUM_CREDIT_CARD_LIST_SIZE = 0;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String cardNumber = request.getParameter("cardNumber");
		String cvvCode = request.getParameter("cvvCode");
		String nameOnCreditCard = request.getParameter("nameOnCreditCard");
		double amount = Double.parseDouble(request.getParameter("amount"));
		CreditCard creditCardBean = new CreditCard();
		creditCardBean.setCreditCardNumber(cardNumber);
		creditCardBean.setCvvCode(cvvCode);
		creditCardBean.setAmount(amount);
		
		CreditCardTransactionsDaoImpl creditCardTransactionsDaoImpl = ContextBeans.getcreateCreditCardTransactionsDao();
		
		    List <CreditCard> retievedCreditCardBeanList = creditCardTransactionsDaoImpl.getCreditCardWithParam(creditCardBean);

			if (retievedCreditCardBeanList.size() == MINIMUM_CREDIT_CARD_LIST_SIZE )  {
		    	LOGGER.error("Invalid credit card details entered for card Number " + cardNumber + " and Cvv code" + cvvCode);
		    	request.setAttribute(INVALID_DETAILS, true);
	        } else {	   
		    
	        	CreditCard retievedCreditCardBean = retievedCreditCardBeanList.get(MINIMUM_CREDIT_CARD_LIST_SIZE);
	        	Customer customerBean = new Customer();
	        	customerBean.setCustomerId(retievedCreditCardBean.getCardOwner());
		    
	        	CustomerDaoImpl createAccountDaoImpl =  ContextBeans.getCreateAccountDao();
	        	Customer retrievedCustomerBean = createAccountDaoImpl.get(customerBean);
			     
	        	if (!nameOnCreditCard.equals(retrievedCustomerBean.getUserName())) {
			    	LOGGER.error("User name - " +  nameOnCreditCard + " is invalid for card number - "+ cardNumber);
			    	request.setAttribute(INVALID_DETAILS, true);
			    	throw new CreditCardException("User name - " +  nameOnCreditCard + " is invalid for card number - "+ cardNumber);
			    	
	        	}
	        	else if (amount < MAXIMUM_AMOUNT) {
	        		
	        		CreditCardHelper creditCardHelper = ContextBeans.getCreditCardHelper();
	        		creditCardHelper.creditCardAmountBorrowedUpdation(retievedCreditCardBean, amount);
	        		LOGGER.info("Successfully borrowed Rs " + amount + " from credit card - " + cardNumber );
	        		request.setAttribute("transactionSuccessful", true);
	        	} else {
	        		LOGGER.error("Invalid Amount entered");
	        		request.setAttribute("invalidAmount", true);
			    	throw new CreditCardException("Invalid Amount entered");
	        	}
		    }
		    
		} catch (CreditCardDBAccessException e) { 
			LOGGER.error(e);
			request.setAttribute("failedDBConnection", true); 
		} catch (CustomerDBAccessException e) { 
			LOGGER.error(e);
			request.setAttribute("failedDBConnection", true); 
		} catch (CreditCardException e) {
			LOGGER.error(e);
		} catch (Exception e) {
			request.setAttribute(INVALID_DETAILS, true);
			LOGGER.error(e);
		} 
		try {
		RequestDispatcher rd = request.getRequestDispatcher("creditCardTransactions.jsp");
		rd.forward(request, response);
		} catch (ServletException e) {
			LOGGER.error(e);
		}catch (IOException e) {
			LOGGER.error(e);
		}
		
		

	}

}

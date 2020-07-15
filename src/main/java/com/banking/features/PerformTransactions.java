package com.banking.features;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;
import com.banking.spring.beans.ContextBeansFactory;
import com.banking.exceptions.AccountsDBAccessException;
import com.banking.exceptions.FinancialTransaction;
import com.banking.exceptions.TransactionDBAccessException;
import com.banking.money.transaction.Account;
import com.banking.money.transaction.TransactionsHelper;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Servlet for performing money transactions from one servlet to other
 */
@WebServlet("/PerformTransactionsServlet")
public class PerformTransactions extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(PerformTransactions.class);
    private HttpServletRequest tempRequest;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		tempRequest = request;
    		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
    		double amount = Double.parseDouble(request.getParameter("amount"));
    		String transactionType = request.getParameter("transactionType");

    		Cookie[] allCookies = request.getCookies();
    		int customerId = 0 ;
    		
    		for (Cookie cookie : allCookies) {
    		   if (cookie.getName().equals("customerId")) {
    			   customerId = Integer.parseInt(cookie.getValue());
    		    }
    		  }
    			
    		Account account = new Account();
    		account.setAccountNumber(accountNumber);
    		account.setAccountBalance(amount);
    				
    		performTransactionAndVerify(account, transactionType, customerId);
    		} catch (DataAccessException e) {
    			request.setAttribute("failedDBConnection", true);
    			LOGGER.error(e);
    		} catch (TransactionException e) {
    		    request.setAttribute("failedDBConnection", true);
    		    LOGGER.error(e);
    		}	catch (FinancialTransaction e) {
    			LOGGER.error(e);
    		} catch (Exception e) {
    			LOGGER.error("failed",e);
    			request.setAttribute("invalidDetails", "INVALID DETAILS");
    		}
    	try {	
    		request = tempRequest;
    		RequestDispatcher rd = request.getRequestDispatcher("moneyTransfer.jsp");
    		rd.forward(request, response);
    		} catch (ServletException e) {
    			LOGGER.error(e);
    		} catch (IOException e) {
    			LOGGER.error(e);
    		}
    }
    
	 /**
     * @author Dipanjan Sengupta 
     * @purpose - Perform Financial Transactions from one account to another and verify 
     * @param - account : Account object storing details of target account
     *          transactionType: "credit/debit"
     *          customerId : Logged in users customerId
     */
	 void performTransactionAndVerify(Account account,String transactionType,int customerId) throws TransactionDBAccessException, AccountsDBAccessException, FinancialTransaction  {
	    	
		 TransactionsHelper transactionsDao = ContextBeansFactory.getTransactionsHelper();
			
		 String status = transactionsDao.performTransaction( account, transactionType, customerId ).getStatus();				
				if (!status.equals("OK")) {
					LOGGER.error(status);
					tempRequest.setAttribute("invalidDetails", status);
					throw new FinancialTransaction("Invalid Details Entered");
				} else {
					LOGGER.info("Transaction successful");
					tempRequest.setAttribute("transactionSuccessful", true);
				}
	    }

}

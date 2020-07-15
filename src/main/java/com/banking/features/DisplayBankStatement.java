package com.banking.features;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.banking.money.transaction.Transaction;
import com.banking.money.transaction.TransactionDaoImpl;
import com.banking.spring.beans.ContextBeansFactory;
import com.banking.exceptions.TransactionDBAccessException;
import com.banking.exceptions.AccountsDBAccessException;
import com.banking.exceptions.BankStatementException;
import com.banking.money.transaction.AccountsDaoImpl;

/**
 * @author Dipanjan Sengupta 
 * @purpose - Servlet for creating a fetching Bank Transactions 
 */
@WebServlet("/displayStatementServlet")
public class DisplayBankStatement extends HttpServlet {
   
	private static final Logger LOGGER = Logger.getLogger(DisplayBankStatement.class);
	private static final  String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	private static final  String DEFAULT_START_TIME = " 00:00:00";
	private static final  String DEFAULT_END_TIME = " 23:59:59";
	private static final  int TRANSACTIONS_LIST_SIZE = 0;
	private static final  String DATE_RANGE = "dateRange";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		try {
			
			dateFrom = request.getParameter("dateFrom");
			dateTo = request.getParameter("dateTo");
		
		
		    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		    Date parsedDate = dateFormat.parse(dateFrom + DEFAULT_START_TIME);
		    Timestamp timestampDateFrom = new Timestamp(parsedDate.getTime());
		    
		    parsedDate = dateFormat.parse(dateTo + DEFAULT_END_TIME);
		    Timestamp timestampDateTo = new Timestamp(parsedDate.getTime());
		    
		    Cookie[] allCookies = request.getCookies();
			int customerId = 0 ;
			
			for (Cookie cookie : allCookies) {
			   if (cookie.getName().equals("customerId")) {
				   customerId = Integer.parseInt(cookie.getValue());
			    }
			  }
            AccountsDaoImpl accountsDaoImplLoggedInUser = ContextBeansFactory.getAcountsDaoImpl();
            int loggedInUsersAccountNumber =  accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerId).getAccountNumber();
	        
		    TransactionDaoImpl transactionDaoImpl = ContextBeansFactory.getTransactionDaoImpl();
			List <Transaction> transactionDetailsBeanList = transactionDaoImpl.getTransactionDetails(
					loggedInUsersAccountNumber,loggedInUsersAccountNumber,timestampDateFrom, timestampDateTo);
			LOGGER.info("Number of transactions retrieved in date range - " + dateFrom + " to " + dateTo + " is - " + transactionDetailsBeanList.size());
						request.setAttribute("bankStatements", transactionDetailsBeanList); 
			request.setAttribute("userAccountNumber", loggedInUsersAccountNumber);
			if (transactionDetailsBeanList.size() == TRANSACTIONS_LIST_SIZE) {
				request.setAttribute("size", TRANSACTIONS_LIST_SIZE);
				request.setAttribute(DATE_RANGE, dateFrom + " - " + dateTo);
				throw new BankStatementException("No transactions were retieved");
			}
			
		} catch (AccountsDBAccessException e) {
			LOGGER.error(e);
			request.setAttribute("failedDBConnection", true); 
		}	catch (TransactionDBAccessException e) {
			LOGGER.error(e);
			request.setAttribute("failedDBConnection", true); 
		}catch (ParseException e) {
			request.setAttribute("size", TRANSACTIONS_LIST_SIZE);
			request.setAttribute(DATE_RANGE, dateFrom + " - " + dateTo);
			LOGGER.error(e);
		} catch (BankStatementException e) {
			LOGGER.error(e);
		} catch (Exception e) {
			request.setAttribute("size", TRANSACTIONS_LIST_SIZE);
			request.setAttribute(DATE_RANGE, dateFrom + " - " + dateTo);
			LOGGER.error(e);
		}
		try {	
			RequestDispatcher rd = request.getRequestDispatcher("displayBankStatement.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

}

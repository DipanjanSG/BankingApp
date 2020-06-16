package com.banking.features;

import java.io.IOException;

import java.io.PrintWriter;
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
import com.banking.spring.beans.ContextBeans;

import com.banking.money.transaction.AccountsDaoImpl;
import com.banking.money.transaction.TransactionsDao;
/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for creating a fetching Bank Transactions 
 */
@WebServlet("/displayStatementServlet")
public class DisplayBankStatement extends HttpServlet {
   
	final static Logger LOGGER = Logger.getLogger(DisplayBankStatement.class);
	private final static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	private final static String DEFAULT_TIME = " 00:00:00";
	private final static int TRANSACTIONS_LIST_SIZE = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		    Date parsedDate = dateFormat.parse(dateFrom + DEFAULT_TIME);
		    Timestamp timestampDateFrom = new Timestamp(parsedDate.getTime());
		    
		    parsedDate = dateFormat.parse(dateTo + DEFAULT_TIME);
		    Timestamp timestampDateTo = new Timestamp(parsedDate.getTime());
		    
		    Cookie[] cookie = request.getCookies();
		    int customerId = Integer.parseInt(cookie[0].getValue());
		    TransactionsDao transactionsDao = new TransactionsDao();
            AccountsDaoImpl accountsDaoImplLoggedInUser = ContextBeans.getAcountsDaoImpl();
            int loggedInUsersAccountNumber =  accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerId).getAccountNumber();
	        
		    TransactionDaoImpl transactionDaoImpl = ContextBeans.getTransactionDaoImpl();
		    Transaction transaction = new Transaction();		
			List <Transaction> transactionDetailsBeanList = transactionDaoImpl.getTransactionDetails(
					loggedInUsersAccountNumber,loggedInUsersAccountNumber,timestampDateFrom, timestampDateTo);
			LOGGER.info("Number of transactions retrieved in date range - " + dateFrom + " to " + dateTo + " is - " + transactionDetailsBeanList.size());
						request.setAttribute("bankStatements", transactionDetailsBeanList); 
			request.setAttribute("userAccountNumber", loggedInUsersAccountNumber);
			if (transactionDetailsBeanList.size() == TRANSACTIONS_LIST_SIZE) {
				request.setAttribute("size", TRANSACTIONS_LIST_SIZE);
				request.setAttribute("dateRange", dateFrom + " - " + dateTo);
			}
			RequestDispatcher rd = request.getRequestDispatcher("displayBankStatement.jsp");
			rd.forward(request, response);
		}
		
		catch (ParseException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		
	}

}

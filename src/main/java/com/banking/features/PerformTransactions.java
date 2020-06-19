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
import com.banking.spring.beans.ContextBeans;
import com.banking.exceptions.MoneyTransferException;
import com.banking.money.transaction.Accounts;
import com.banking.money.transaction.TransactionsHelper;

/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for performing money transactions from one servlet to other
 */
@WebServlet("/PerformTransactionsServlet")
public class PerformTransactions extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(PerformTransactions.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String transactionType = request.getParameter("transactionType");

		Cookie[] allCookies = request.getCookies();
		int customerId = Integer.parseInt(allCookies[0].getValue());
		
		Accounts accounts = new Accounts();
		accounts.setAccountNumber(accountNumber);
		accounts.setAccountBalance(amount);
				
		TransactionsHelper transactionsDao = ContextBeans.getTransactionsHelper();
		
						
			if (!transactionsDao.performTransaction( accounts, transactionType, customerId )) {
				LOGGER.error("Invalid Details Entered");
				request.setAttribute("invalidDetails", true);
				throw new MoneyTransferException("Invalid Details Entered");
			} else {
				LOGGER.info("Transaction successful");
				request.setAttribute("transactionSuccessful", true);
			}
		
		} catch (DataAccessException e) {
			request.setAttribute("failedDBConnection", true);
			LOGGER.error(e);
		} catch (TransactionException e) {
		    request.setAttribute("failedDBConnection", true);
		    LOGGER.error(e);
		}	catch (MoneyTransferException e) {
			LOGGER.error(e);
		} catch (Exception e) {
			LOGGER.error(e);
			request.setAttribute("invalidDetails", true);
		}
	try {	
		RequestDispatcher rd = request.getRequestDispatcher("moneyTransfer.jsp");
		rd.forward(request, response);
		} catch (ServletException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

}

package com.banking.features;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banking.spring.beans.ContextBeans;
import com.banking.exceptions.MoneyTransferException;
import com.banking.money.transaction.Accounts;
import com.banking.money.transaction.AccountsDaoImpl;
import com.banking.money.transaction.TransactionsDao;

/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for performing money transactions from one servlet to other
 */
@WebServlet("/PerformTransactionsServlet")
public class PerformTransactions extends HttpServlet {

	final static Logger LOGGER = Logger.getLogger(PerformTransactions.class);

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
				
		TransactionsDao transactionsDao = ContextBeans.getTransactionsDao();
		
						
			if (!transactionsDao.performTransaction( accounts, transactionType, customerId )) {
				LOGGER.error("Invalid Details Entered");
				request.setAttribute("invalidDetails", true);
				throw new MoneyTransferException("Invalid Details Entered");
			} else {
				LOGGER.info("Transaction successful");
				request.setAttribute("transactionSuccessful", true);
			}
			
		    
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (MoneyTransferException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			LOGGER.error(e);
			request.setAttribute("invalidDetails", true);
		}
		RequestDispatcher rd = request.getRequestDispatcher("moneyTransfer.jsp");
		rd.forward(request, response);

	}

}

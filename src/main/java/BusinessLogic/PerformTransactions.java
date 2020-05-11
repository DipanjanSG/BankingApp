package BusinessLogic;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Login.LoginBean;
import Login.LoginDao;
import Transactions.AccountsBean;
import Transactions.TransactionsDao;
import configs.ContextBeans;

/**
 * Servlet implementation class PerformTransactions
 */
@WebServlet("/PerformTransactionsServlet")
public class PerformTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String transactionType = request.getParameter("transactionType");

		Cookie[] allCookies = request.getCookies();
		int customerId = Integer.parseInt(allCookies[0].getValue());
		
		AccountsBean accountsBean = ContextBeans.getAccountsBean();
		accountsBean.setAccountNumber(accountNumber);
		accountsBean.setAccountBalance(amount);
		
		
		TransactionsDao transactionsDao = new TransactionsDao();
		try {
			response.setContentType("text/html");
			int loggedInUsersAccountNumber = transactionsDao.getAccountNumber(customerId);
			if (!transactionsDao.performTransaction( accountsBean, transactionType, loggedInUsersAccountNumber )) {
				PrintWriter out = response.getWriter();
				out.println("<h2>Invalid Details<h2>");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<h2>Transaction successful1<h2>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package CustomerActivities;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import Transactions.Accounts;
import Transactions.AccountsDaoImpl;
import Transactions.TransactionsDao;
import configs.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for performing money transactions from one servlet to other
 */
@WebServlet("/PerformTransactionsServlet")
public class PerformTransactions extends HttpServlet {

	final static Logger logger = Logger.getLogger(PerformTransactions.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String transactionType = request.getParameter("transactionType");

		Cookie[] allCookies = request.getCookies();
		int customerId = Integer.parseInt(allCookies[0].getValue());
		
		Accounts accounts = ContextBeans.getAccountsBean();
		accounts.setAccountNumber(accountNumber);
		accounts.setAccountBalance(amount);
				
		TransactionsDao transactionsDao = ContextBeans.getTransactionsDao();
		try {
			response.setContentType("text/html");				
			PrintWriter out = response.getWriter();
			out.println("<body style=background-color:orange;>");
			
			if (!transactionsDao.performTransaction( accounts, transactionType, customerId )) {
				out.println("<h2>Invalid Details<h2>");
			} else {
				out.println("<h2>Transaction successful<h2>");
			}
			out.println("</body>");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

}

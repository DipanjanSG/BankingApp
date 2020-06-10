package CustomerActivities;

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

import TransactionDetails.Transaction;
import TransactionDetails.TransactionDaoImpl;
import Transactions.AccountsDaoImpl;
import Transactions.TransactionsDao;
import configs.ContextBeans;
/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for creating a fetching Bank Transactions 
 */
@WebServlet("/displayStatementServlet")
public class DisplayBankStatement extends HttpServlet {
   
	final static Logger logger = Logger.getLogger(DisplayBankStatement.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse(dateFrom + " 00:00:00");
		    Timestamp timestampDateFrom = new Timestamp(parsedDate.getTime());
		    
		    parsedDate = dateFormat.parse(dateTo + " 00:00:00");
		    Timestamp timestampDateTo = new Timestamp(parsedDate.getTime());
		    
		    Cookie[] cookie = request.getCookies();
		    int customerId = Integer.parseInt(cookie[0].getValue());
		    TransactionsDao transactionsDao = new TransactionsDao();
            AccountsDaoImpl accountsDaoImplLoggedInUser = ContextBeans.getAcountsDaoImpl();
            int loggedInUsersAccountNumber =  accountsDaoImplLoggedInUser.getAccountWithCustomerId(customerId).getAccountNumber();
	        
		    TransactionDaoImpl transactionDaoImpl = ContextBeans.getTransactionDaoImpl();
		    Transaction transaction = ContextBeans.getTransactionDetailsBean();		
			List <Transaction> transactionDetailsBeanList = transactionDaoImpl.getTransactionDetails(
					loggedInUsersAccountNumber,loggedInUsersAccountNumber,timestampDateFrom, timestampDateTo);
			logger.info("Number of transactions retrieved in date range - " + dateFrom + " to " + dateTo + " is - " + transactionDetailsBeanList.size());
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			request.setAttribute("bankStatements", transactionDetailsBeanList); 
			request.setAttribute("userAccountNumber", loggedInUsersAccountNumber);
			if (transactionDetailsBeanList.size() == 0) {
				request.setAttribute("size", 0);
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

package BusinessLogic;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.plaf.synth.SynthSeparatorUI;
import Login.LoginBean;
import Login.LoginDao;
import TransactionDetails.Transaction;
import TransactionDetails.TransactionDaoImpl;
import Transactions.Accounts;
import Transactions.TransactionsDao;
import configs.ContextBeans;

/**
 * Servlet implementation class PerformTransactions
 */
@WebServlet("/displayStatementServlet")
public class DisplayBankStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		    int loggedInUsersAccountNumber = transactionsDao.getAccountNumber(customerId);
		    TransactionDaoImpl transactionDaoImpl = ContextBeans.getTransactionDaoImpl();
		    Transaction transaction = ContextBeans.getTransactionDetailsBean();		
			List <Transaction> transactionDetailsBeanList = transactionDaoImpl.getTransactionDetails(
					loggedInUsersAccountNumber,loggedInUsersAccountNumber,timestampDateFrom, timestampDateTo);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

				
//			for (Transaction transactionDetailsBean : transactionDetailsBeanList ) {
//				out.println("<h2>"+ transactionDetailsBean.toString()+ "<h2>");
//			}
//			
			request.setAttribute("bankStatements", transactionDetailsBeanList); 
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/displayBankStatement.jsp");
			rd.forward(request, response);
			
		}
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}

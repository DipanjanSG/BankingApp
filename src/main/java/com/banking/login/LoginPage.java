package com.banking.login;

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
import com.banking.exceptions.CredentialsDBAccessException;


/**
 * @author Dipanjan Sengupta 
 * @purpose - Servlet for main page after successful login
 */
@WebServlet("/LoginPageServlet")
public class LoginPage extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(LoginPage.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
		String userName = request.getParameter("uname");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Credentials credentials = new Credentials(userName, password);
		CredentialsHelper loginDao = new CredentialsHelper();
		
			
			Integer customerId = loginDao.validateCredentials(credentials);
			if ( customerId == -1) {		
                request.setAttribute("invalidCredentials", true); 
        		RequestDispatcher rd = request.getRequestDispatcher("loginPage.jsp");
        		rd.forward(request, response);
        		
			}
			else {
				Cookie cookie = new Cookie("customerId", customerId.toString());
				response.addCookie(cookie);			
				out.println("<head>");
				out.println("<title> Bank of Edureka</title>");
				out.println("</head>");
				out.println("<body style='background-color:orange;'");
				out.println("<p>");
				out.println("<h2 input style=\"margin-left: 30em\";> Banking System </h2>");
				out.println("<a href = 'createAccount.jsp'> Create Account </a> <br>");
				out.println("<a href = 'creditCardTransactions.jsp'> Authorize Credit Card Amount</a> <br>");
				out.println("<a href = 'moneyTransfer.jsp' > Transactions </a> <br>");
				out.println("<a href = 'displayBankStatement.jsp'> Display Bank Statement</a> <br>");
				out.println("</p>");
				out.println("</body>");
			}
		} catch (CredentialsDBAccessException e) {
			LOGGER.error(e);
			request.setAttribute("failedDBConnection", true);
    	try {
    		RequestDispatcher rd = request.getRequestDispatcher("loginPage.jsp");
    		rd.forward(request, response);
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		} catch (Exception e) {
			LOGGER.error(e);
		} 
		}
	
	}


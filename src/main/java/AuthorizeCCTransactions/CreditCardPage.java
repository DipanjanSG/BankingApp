package AuthorizeCCTransactions;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Login.LoginBean;
import Login.LoginDao;

/**
 * Servlet implementation class Login2
 */
@WebServlet("/authorizeCreditCardTransaction")
public class CreditCardPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
					
				out.println("<head>");
				out.println("<title> Bank of Edureka</title>");
				out.println("</head>");
				out.println("<body style='background-color:orange;'");
				out.println("<p>");
				out.println("<a href = 'dummy'></a> <br>");
				out.println("<h2> Bank of Edureka </h2>");
				out.println("<h3>Authorize Credit Card Transaction </h3>");
				out.println("<form action=\"AuthorizeCCTransactionsServlet\" >");
				out.println("<label for=\"nameOnCreditCard\">Name:</label>  <input type = \"text\" id = \"nameOnCreditCard\" name = \"nameOnCreditCard\"> <br> <br>\r\n");
				out.println("<label for=\"cardNumber\">Card Number:</label>  <input type = \"text\" id = \"cardNumber\" name = \"cardNumber\"> <br> <br>\r\n" + 
						"");
				out.println("<label for=\"cvvCode\">CVV2/CVC2:</label>  <input type = \"text\" id = \"cvvCode\" name = \"cvvCode\"> <br> <br>\r\n" + 
						"");
				out.println("<label for=\"amount\">Amount:</label>  <input type = \"text\" id = \"amount\" name = \"amount\"> <br> <br>\r\n" + 
						"");
				out.println("<button type=\"submit\" >Authorize</button>");
				out.println("</form>");
				out.println("</p>");
			}
}

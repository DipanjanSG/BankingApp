package Login;



import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.TypedQuery;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

import AuthorizeCCTransactions.CustomersDao;
import Login.LoginBean;
import Login.LoginDao;

/**
 * Servlet implementation class Login2
 */
@WebServlet("/LoginPageServlet")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("uname");
		String password = request.getParameter("password");
		
		Credentials credentials = new Credentials(userName, password);
		
		LoginBean loginBean = new LoginBean(userName, password);
		LoginDao loginDao = new LoginDao();
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Integer customerId = loginDao.validateCredentials(credentials);
			if ( customerId == 0) {
				out.println("<h2>Invalid Credentials<h2>");
			}
			else {
				Cookie cookie = new Cookie("customerId", customerId.toString());
				CustomersDao customersDao = new CustomersDao();
				//customersDao.fetchRecordWithUserName();
				response.addCookie(cookie);			
				out.println("<head>");
				out.println("<title> Bank of Edureka</title>");
				out.println("</head>");
				out.println("<body style='background-color:orange;'");
				out.println("<p>");
				out.println("<a href = 'dummy'></a> <br>");
				out.println("<h2> Banking System </h2>");
				out.println("<a href = 'enterAccountDetailsServlet'> Create Account </a> <br>");
				out.println("<a href = 'authorizeCreditCardTransaction'> Authorize Credit Card Amount</a> <br>");
				out.println("<a href = 'transactionsPageServlet' > Transactions </a> <br>");
				out.println("<a href = 'displayBankStatement.jsp'> Display Bank Statement</a> <br>");
				out.println("<a href = 'dummy'></a> <br>");
				out.println("</p>");
				out.println("</body>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}


}

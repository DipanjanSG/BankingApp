package CreateAccount;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CreateAccount.Customer;
import CreateAccount.CustomerDaoImpl;
import Login.LoginBean;
import Login.LoginDao;
import java.sql.ResultSet;

/**
 * Servlet implementation class createAccount
 */
@WebServlet("/enterAccountDetailsServlet")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<head>");
		out.println("<title> Bank of Edureka</title>");
		out.println("</head>");
		out.println("<body style='background-color:orange;'>");
		out.println("<form action='createAccountServlet'>");
		out.println("<h2> Account Creation Screen </h2>");
		out.println("<pre>");
		out.println("<label for='uname'>Name:     </label>  <input type = 'text' id ='uName' name ='uName'> <br> <br>");
		out.println(
				"<label for='dateOfBirth'>DOB:      </label>  <input type = 'text' id ='dateOfBirth'name ='dateOfBirth'> <br> <br>");
		out.println(
				"<label for='address'>Address:  </label>  <input type = 'text' id ='address' name ='address'> <br> <br>");
		out.println(
				"<label for='emailId'>Email Id: </label>  <input type = 'text' id ='emailId' name ='emailId'> <br> <br>");

		out.println("<label for='bankAccountType'>Type Of Account:</label> <select name='bankAccountType' size='1'>");
		out.println("  <option value='volvo'>Savings</option>");
		out.println("  <option value='current'>Current</option>");
		out.println("</select>");
		out.println("<br> <br>");
		out.println("<input type='submit' value='Create Account'>");
		out.println("</pre>");
		out.println("</form>");
		out.println("</body>");

	}
}

package Transactions;

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

/**
 * Servlet implementation class TransactionsPage
 */
@WebServlet("/transactionsPageServlet")
public class TransactionsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			  String name = cookies[i].getName();
			  String value = cookies[i].getValue();
			}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			out.println("<head>");
			out.println("<title> Bank of Edureka</title>");
			out.println("</head>");
			out.println("<body style='background-color:orange;'");
			out.println("<p>");
			out.println("<a href = 'dummy'></a> <br>");
			out.println("<h3> Perform Debit or Credit Operation </h3>");
		out.println("<form action = \"PerformTransactionsServlet\">");

			out.println("<label for=\"accountNumber\">Account Number:</label>  <input type = \"text\" id = \"accountNumber\"   name = \"accountNumber\"> <br> <br>");
			out.println("<label for=\"amount\">Amount:</label>                 <input type = \"text\" id = \"amount\"          name = \"amount\"> <br> <br>");
			out.println("                                                    <input type = \"hidden\" id = \"transactionType\" name = \"transactionType\" value = \"notSet\">");
			out.println("<button type=\"submit\" onclick=\"setValueDepositOrWithDraw(this)\" value=\"null\" name = \"debit\" >Debit From</button>");
			out.println("<button type=\"submit\" onclick=\"setValueDepositOrWithDraw(this)\" value=\"null\" name = \"credit\">Credit To</button>");
			out.println("</form>");
			out.println("</p>");
			out.println("</body>");
			out.println("<script type=\"text/javascript\" >");

			out.println("function setValueDepositOrWithDraw(button) {");
			out.println("	          document.getElementById(\"transactionType\").value = button.name;");
			out.println("          }       "); 
			out.println("</script>");
			out.println("</html>");
	}

}

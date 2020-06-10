package CustomerActivities;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import CreateAccount.Customer;
import CreateAccount.CustomerDaoImpl;
import Login.Credentials;
import Transactions.Accounts;
import configs.ContextBeans;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for creating a new account
 */
@WebServlet("/createAccountServlet")
public class CreateAccount extends HttpServlet {
	
	final static Logger logger = Logger.getLogger(CreateAccount.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("uName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      
		Date dateOfBirth = null;
		try {
			dateOfBirth = formatter.parse(request.getParameter("dateOfBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String address = request.getParameter("address");
		String emailId = request.getParameter("emailId");
		String bankAccountType = request.getParameter("bankAccountType");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (userName!= null && formatter!= null && address!=null && emailId!= null && emailId.contains("@") && bankAccountType!= null) {
			
			CustomerDaoImpl createAccountDao =  ContextBeans.getCreateAccountDao();
			Accounts accountsBean = ContextBeans.getAccountsBean();
			accountsBean.setAccountBalance(0.0);
			accountsBean.setBankAccountType(bankAccountType);
			
			Set <Accounts> allAccountsHeld = new HashSet();
			allAccountsHeld.add(accountsBean);
			
			Credentials credentials = ContextBeans.getCredentials();
			credentials.createUserIdAndPassword(userName);
		
			Customer customerBean = ContextBeans.getCustomerBean();
			customerBean.setUserName(userName);
			customerBean.setDateOfBirth(dateOfBirth);
			customerBean.setAddress(address);
			customerBean.setEmailId(emailId);
			customerBean.setAllAccountsHeld(allAccountsHeld);
			customerBean.setCredentials(credentials);
			Integer customerNumber = createAccountDao.save(customerBean);
			if ( customerNumber != null)
			{ 	response.setContentType("text/html");
				logger.info("New Customer record created with customer Id -" + customerNumber);
			    out.println("<body style='background-color:orange;'>");
				out.println("<h2>Account created successfully<h2>");
			} else {
				logger.error("Account has not been created for " + userName);
				out.println("<h2>User Account is not created<h2>");
			}
		}
		else {
			logger.error("All values for creating account not entered - " + userName);
		    out.println("<h2>All Information not entered<h2>");
		}
		 out.println("</body>");
	}
}

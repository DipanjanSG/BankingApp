package CustomerActivities;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import AuthorizeCCTransactions.CreditCard;
import AuthorizeCCTransactions.CreditCardHelper;
import AuthorizeCCTransactions.CreditCardTransactionsDaoImpl;
import CreateAccount.Customer;
import CreateAccount.CustomerDaoImpl;
import configs.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - Servlet for Credit Card Transactions
 */

@WebServlet("/AuthorizeCCTransactionsServlet")
public class AuthorizeCCTransactions extends HttpServlet {
	
	final static Logger logger = Logger.getLogger(AuthorizeCCTransactions.class);
    final static int maximumAmount = 100000;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cardNumber = request.getParameter("cardNumber");
		String cvvCode = request.getParameter("cvvCode");
		String nameOnCreditCard = request.getParameter("nameOnCreditCard");
		double amount = Double.parseDouble(request.getParameter("amount"));
		CreditCard creditCardBean = ContextBeans.getCreditCardBean();
		creditCardBean.setCreditCardNumber(cardNumber);
		creditCardBean.setCvvCode(cvvCode);
		creditCardBean.setAmount(amount);
		
		CreditCardTransactionsDaoImpl creditCardTransactionsDaoImpl = ContextBeans.getcreateCreditCardTransactionsDao();
		try {
			response.setContentType("text/html");				
			PrintWriter out = response.getWriter();
			out.println("<body style=background-color:orange;>");
		    CreditCard retievedCreditCardBean = creditCardTransactionsDaoImpl.getCreditCardWithParam(creditCardBean);

		    if (retievedCreditCardBean == null ) {
		    	logger.error("Invalid credit card details entered for card Number " + cardNumber + " and Cvv code" + cvvCode);
		    	out.println("<h2>Invalid Credit Card Details</h2>");
	        } else {	   
		    
	        	Customer customerBean = ContextBeans.getCustomerBean();
	        	customerBean.setCustomerId(retievedCreditCardBean.getCardOwner());
		    
	        	CustomerDaoImpl createAccountDaoImpl =  ContextBeans.getCreateAccountDao();
	        	Customer retrievedCustomerBean = createAccountDaoImpl.get(customerBean);
			     
	        	if (!nameOnCreditCard.equals(retrievedCustomerBean.getUserName())) {
			    	logger.error("User name - " +  nameOnCreditCard + " is invalid for card number - "+ cardNumber);
	        		out.println("<h2>Invalid Owner name entered</h2>");
	        	}
	        	else if (amount < maximumAmount) {
	        		
	        		CreditCardHelper creditCardHelper = ContextBeans.getCreditCardHelper();
	        		creditCardHelper.creditCardAmountBorrowedUpdation(retievedCreditCardBean, amount);
	        		logger.info("Successfully borrowed Rs " + amount + " from credit card - " + cardNumber );
	        		out.println("<h2>Transaction Successful</h2>");
	        	} else {
	        		logger.error("Invalid Amount greter than entered");
	        		out.println("<h2>Invalid Amount Entered</h2>");
	        	}
		    }
			out.println("</body>");
		    
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

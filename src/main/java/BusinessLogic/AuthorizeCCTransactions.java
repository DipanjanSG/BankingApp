package BusinessLogic;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import AuthorizeCCTransactions.CreditCardBean;
import AuthorizeCCTransactions.CreditCardHelper;
import AuthorizeCCTransactions.CreditCardTransactionsDao;
import AuthorizeCCTransactions.CustomersDao;
import CreateAccount.CustomerBean;
import Login.LoginBean;
import Login.LoginDao;
import Transactions.AccountsBean;
import Transactions.TransactionsDao;
import configs.ContextBeans;

/**
 * Servlet implementation class PerformTransactions
 */


@WebServlet("/AuthorizeCCTransactionsServlet")

public class AuthorizeCCTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(AuthorizeCCTransactions.class);
    public AuthorizeCCTransactions() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cardNumber = request.getParameter("cardNumber");
		String cvvCode = request.getParameter("cvvCode");
		String nameOnCreditCard = request.getParameter("nameOnCreditCard");
		double amount = Double.parseDouble(request.getParameter("amount"));
		logger.info("My first log");
		//CreditCardBean creditCardBean = (CreditCardBean) ContextBeans.getNewContext().getBean("creditCardBean");
		CreditCardBean creditCardBean = ContextBeans.getCreditCardBean();
		creditCardBean.setCreditCardNumber(cardNumber);
		creditCardBean.setCvvCode(cvvCode);
		creditCardBean.setAmount(amount);
				
		CreditCardTransactionsDao creditCardTransactionsDao = new CreditCardTransactionsDao();
		CustomersDao customersDao = new CustomersDao();
		try {
			response.setContentType("text/html");				
			PrintWriter out = response.getWriter();
			out.println("<body style=background-color:orange;>");
		    CreditCardBean tempRetievedCreditCardBean = creditCardTransactionsDao.validate(creditCardBean);
		    CreditCardBean retievedCreditCardBean = ContextBeans.getCreditCardBean();
		    
		    retievedCreditCardBean.setCardOwner(tempRetievedCreditCardBean.getCardOwner());
		    retievedCreditCardBean.setCreditCardNumber(tempRetievedCreditCardBean.getCreditCardNumber());
		    retievedCreditCardBean.setAmount(tempRetievedCreditCardBean.getAmount());
		    retievedCreditCardBean.setCvvCode(tempRetievedCreditCardBean.getCvvCode());
		    		    
		    if (retievedCreditCardBean == null ) {
	        	System.out.println("Invalid Credit Card Details");
	        }
		    CustomerBean customerBean = ContextBeans.getCustomerBean();

		    
			CustomerBean retrievedCustomerBean = customersDao.fetchRecordWithUserName(customerBean);
			if (amount < 100000 && nameOnCreditCard.equals(retrievedCustomerBean.getUserName())) {
				out.println("Transaction Successful");
				CreditCardHelper creditCardHelper = ContextBeans.getCreditCardHelper();
				creditCardHelper.creditCardAmountBorrowedUpdation(retievedCreditCardBean, amount);
			} else {
				out.println("Invalid Details for Credit Card entered");
			}
			out.println("</body>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

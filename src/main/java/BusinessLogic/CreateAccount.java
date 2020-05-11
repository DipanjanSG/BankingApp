package BusinessLogic;


import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AuthorizeCCTransactions.CreditCardBean;
import CreateAccount.CustomerBean;
import CreateAccount.CreateAccountDao;
import Login.Credentials;
import Login.LoginBean;
import Login.LoginDao;
import Transactions.AccountsBean;
import configs.ContextBeans;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Servlet implementation class createAccount
 */
@WebServlet("/createAccountServlet")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("uName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      
		Date dateOfBirth = null;
		try {
			dateOfBirth = formatter.parse(request.getParameter("dateOfBirth"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String address = request.getParameter("address");
		String emailId = request.getParameter("emailId");
		String bankAccountType = request.getParameter("bankAccountType");
		
		CreateAccountDao createAccountDao = new CreateAccountDao();
		AccountsBean accountsBean = ContextBeans.getAccountsBean();
		accountsBean.setAccountBalance(0.0);
		accountsBean.setBankAccountType(bankAccountType);
		
		Set <AccountsBean> allAccountsHeld = new HashSet();
		allAccountsHeld.add(accountsBean);
		
		Credentials credentials = ContextBeans.getCredentials();
		credentials.createUserIdAndPassword(userName);

		try {
			CustomerBean customerBean = ContextBeans.getCustomerBean();
			customerBean.setUserName(userName);
			customerBean.setDateOfBirth(dateOfBirth);
			customerBean.setAddress(address);
			customerBean.setEmailId(emailId);
			customerBean.setAllAccountsHeld(allAccountsHeld);
			customerBean.setCredentials(credentials);
			createAccountDao.validate(customerBean);
		
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}

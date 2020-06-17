package com.banking.features;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;
import com.banking.account.creation.Customer;
import com.banking.account.creation.CustomerDaoImpl;
import com.banking.exceptions.AccountCreationException;
import com.banking.login.Credentials;
import com.banking.spring.beans.ContextBeans;
import com.banking.money.transaction.Accounts;
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
	
	static final Logger LOGGER = Logger.getLogger(CreateAccount.class);
	private static final  double MINIMUM_STARTING_BALANCE = 0.0;
	private static final String EMAIL_ID_AT_THE_RATE = "@";
	private static final String ALL_DETAILS_NOT_ENTERED = "allDetailsNotEntered";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String userName = request.getParameter("uName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = null;
		
		
			dateOfBirth = formatter.parse(request.getParameter("dateOfBirth"));
			String address = request.getParameter("address");
			String emailId = request.getParameter("emailId");
			String bankAccountType = request.getParameter("bankAccountType");
			
			Customer customerBean = new Customer();
			customerBean.setUserName(userName);
			customerBean.setDateOfBirth(dateOfBirth);
			customerBean.setAddress(address);
			customerBean.setEmailId(emailId);
		
		if (userName.length() > 0 && dateOfBirth.toString().length() > 0 && address.length() > 0 && emailId.length() > 0 && emailId.contains(EMAIL_ID_AT_THE_RATE) ) {
			
			CustomerDaoImpl createAccountDao =  ContextBeans.getCreateAccountDao();
			Accounts accountsBean = new Accounts();
			accountsBean.setAccountBalance(MINIMUM_STARTING_BALANCE);
			accountsBean.setBankAccountType(bankAccountType);
			
			Set <Accounts> allAccountsHeld = new HashSet<Accounts>();
			allAccountsHeld.add(accountsBean);
			
			Credentials credentials = new Credentials();
			credentials.createUserIdAndPassword(userName);
			
			customerBean.setAllAccountsHeld(allAccountsHeld);
			customerBean.setCredentials(credentials);
			Integer customerNumber = createAccountDao.save(customerBean);
			
			if ( customerNumber != null)
			{ 	
				LOGGER.info("New Customer record created with customer Id -" + customerNumber);
				request.setAttribute("customerNumber", customerNumber);
			    
			} else {
				
				LOGGER.error("Account has not been created for " + userName);
				request.setAttribute("accountNotCreated", true);
				request.setAttribute("userName", userName);
				throw new AccountCreationException("Account has not been created for " + customerBean.toString());
			}
		} else {
			request.setAttribute(ALL_DETAILS_NOT_ENTERED, true);
			LOGGER.error("All values for creating account not entered - ");
			throw new AccountCreationException("All values for creating account not entered " + customerBean.toString() );
			}
		} catch (DataAccessException e) {
			request.setAttribute("failedDBConnection", true);
			LOGGER.error(e);
		  } catch (TransactionException e) {
		    request.setAttribute("failedDBConnection", true);
		    LOGGER.error(e);
		  } catch (ParseException ex) {
			LOGGER.error(ex);
			request.setAttribute(ALL_DETAILS_NOT_ENTERED, true);
		  } catch (AccountCreationException ex) {
			LOGGER.error(ex);
		  } catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(ALL_DETAILS_NOT_ENTERED, true);
			LOGGER.error(e);
		}
	try {	
		RequestDispatcher rd = request.getRequestDispatcher("createAccount.jsp");
		rd.forward(request, response);
	} catch (ServletException e) {
		LOGGER.error(e);
	}catch (IOException e) {
		LOGGER.error(e);
	}
	}
}

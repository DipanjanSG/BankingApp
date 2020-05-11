package TransactionDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import BusinessLogic.SessionFactoryCreation;
import Transactions.AccountsBean;
import configs.HibernateUtils;

public class TransactionDetailsDao {

	public List<TransactionDetailsBean> getTransactionDetails(int fromAccount,int toAccount, Timestamp dateFrom , Timestamp dateTo) throws ClassNotFoundException, IOException {
                
		Session session = SessionFactoryCreation.getSessionInstance();
        	
        String hql = "from TransactionDetails.TransactionDetailsBean where (fromAccount = :fromAccount or toAccount = :toAccount)  and (dateOfTransaction >= :dateFrom) and (dateOfTransaction <= :dateTo)";
		TypedQuery<TransactionDetailsBean> query = session.createQuery(hql);
		
		query.setParameter("fromAccount",fromAccount);
		query.setParameter("toAccount",toAccount);
		query.setParameter("dateFrom",dateFrom);
		query.setParameter("dateTo",dateTo);
		
        return query.getResultList();
	}
	
}

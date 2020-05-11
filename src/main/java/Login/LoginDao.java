package Login;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import BusinessLogic.SessionFactoryCreation;
import CreateAccount.CustomerBean;
import configs.HibernateUtils;

public class LoginDao {
	public int validateCredentials(Credentials credentials) throws ClassNotFoundException {
		    Session session = SessionFactoryCreation.getSessionInstance();
        	String hql = "from Login.Credentials where userName = :userName and password = :password";
			TypedQuery<Credentials> query = session.createQuery(hql);
			query.setParameter("userName",credentials.userName);
			query.setParameter("password",credentials.password);
			List <Credentials> retrievedCredentials = query.getResultList();
			if ( retrievedCredentials.size() >= 1 ) {
				return retrievedCredentials.get(0).getCustomerId();
			}
			
	        return 0;
	}
}

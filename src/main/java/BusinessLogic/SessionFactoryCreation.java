package BusinessLogic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import configs.HibernateUtils;

public class SessionFactoryCreation {
	
	 private static SessionFactory sFactory = null ;
	 private static Session session = null;
     
	public static Session getSessionInstance() {
		
		if (sFactory == null) {
			 sFactory = HibernateUtils.getSessionFactory();
		     session = sFactory.openSession();
		}
		
		return session;  
	}
	
	  
	public static void destroySessionInstance() {
			
			if (sFactory != null) {
				 session.close();
				 sFactory.close();			 
			}
	}
}

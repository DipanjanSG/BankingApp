package configs;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	public static SessionFactory getSessionFactory() {
		Configuration config = new Configuration();
		config.configure("configs/hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		return sf;	
	}

}

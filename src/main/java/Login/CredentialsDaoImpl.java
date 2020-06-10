package Login;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on credentials table
 */
public class CredentialsDaoImpl {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public void save(Credentials credential) {
		hibernateTemplate.save(credential);
	}
	
	@Transactional(readOnly = false)
	public void update(Credentials credential) {
		hibernateTemplate.update(credential);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(Credentials credential) {
		hibernateTemplate.delete(credential);
	}
	
	@Transactional(readOnly = false)
	public List<Credentials> getcredentials() {
		return hibernateTemplate.loadAll(Credentials.class);
	}

    public Credentials getCredentialDetails( Credentials credentials ) throws ClassNotFoundException, IOException {
		
    	Credentials retrievedCredential = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Credentials.class);
	    criteria.add(Restrictions.eq("userName", credentials.getUserName()));
	    criteria.add(Restrictions.eq("password", credentials.getPassword()));
	    List<Credentials> credentalList = ((List<Credentials>) hibernateTemplate.findByCriteria(criteria));
	    if (credentalList.size() > 0) {
	    	retrievedCredential = credentalList.get(0);
	    }
        return retrievedCredential;
	}
}

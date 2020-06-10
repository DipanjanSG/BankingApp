package Transactions;

import java.io.IOException;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import CreateAccount.Customer;
import configs.ContextBeans;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on accounts table
 */
public class AccountsDaoImpl implements AccountsDao{

	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional(readOnly = false)
	public Accounts get(Accounts accountsBean) {
		return hibernateTemplate.get(Accounts.class, accountsBean.accountNumber);
		
	}
    	
	@Transactional(readOnly = false)
	public void save(Accounts accountsBean) {
		hibernateTemplate.save(accountsBean);		
	}

	@Transactional(readOnly = false)
	public void update(Accounts accountsBean) {
		hibernateTemplate.update(accountsBean);
	}

	@Transactional(readOnly = false)
	public void delete(Accounts accountsBean) {
		hibernateTemplate.delete(accountsBean);
		
	}

	@Transactional(readOnly = false)
	public List<Accounts> getAllAccounts() {
		return hibernateTemplate.loadAll(Accounts.class);
	}

    public Accounts getAccountWithCustomerId(int customerId) throws ClassNotFoundException, IOException {
        Customer customer = ContextBeans.getCustomerBean();
        customer.setCustomerId(customerId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Accounts.class);
		criteria.add(Restrictions.eq("customerBean", customer));
		
        return ((List<Accounts>) hibernateTemplate.findByCriteria(criteria)).get(0);
	}

}

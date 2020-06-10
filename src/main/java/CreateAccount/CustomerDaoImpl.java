package CreateAccount;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dipanjan Sengupta
 * @purpose - HibernateTemplate for operations on customer table
 */
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional(readOnly = false)
	public Integer save(Customer customer) {
		return (Integer)hibernateTemplate.save(customer);
		
	}
	
	@Transactional(readOnly = false)
	public void update(Customer customer) {
		hibernateTemplate.update(customer);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(Customer customer) {
		hibernateTemplate.delete(customer);
	}
	
	@Transactional(readOnly = false)
	public List<Customer> getCustomers() {
		return hibernateTemplate.loadAll(Customer.class);
	}

	@Transactional(readOnly = false)
	public Customer get(Customer customer) {
		return hibernateTemplate.get(Customer.class, customer.getCustomerId());
	}
}

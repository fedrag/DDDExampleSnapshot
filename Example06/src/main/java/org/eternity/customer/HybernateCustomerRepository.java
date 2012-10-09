package org.eternity.customer;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HybernateCustomerRepository extends HibernateDaoSupport implements CustomerRepository{
	public HybernateCustomerRepository(){
	}
	
	@SuppressWarnings("unchecked")
	public Customer find(String customerNumber) {
		try{
			List<Customer> result = (List<Customer>)getHibernateTemplate().find("FROM Customer where customer_number=?", customerNumber);
			//List<Customer> result = (List<Customer>)getHibernateTemplate().find("select id,customer_number,name,address,mileage,limit_price  from customers");
			if (result != null && result.size() > 0) {
				return result.get(0);
			}   
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void save(Customer customer) {
		getHibernateTemplate().save(customer);
	}


}
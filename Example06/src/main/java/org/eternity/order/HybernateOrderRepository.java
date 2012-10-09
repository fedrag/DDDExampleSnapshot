package org.eternity.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eternity.customer.Customer;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HybernateOrderRepository extends HibernateDaoSupport implements OrderRepository{
		public HybernateOrderRepository(){
		}
		 
		@SuppressWarnings("unchecked")
		public Order find(String orderId) {
			//select o from Order o join o.items i where i.product=:product
			
			List<Order> result = (List<Order>)getHibernateTemplate().find("select o from Order o join o.lineItems i where i.order.id=?", orderId);
			if (result != null && result.size() > 0) {
				return (Order)result.get(0);
			}
			return null;
		}
		 
		@SuppressWarnings("unchecked")
		public Set<Order> findAll() {
			return new HashSet<Order>(getHibernateTemplate().loadAll(Order.class));
		}
		 
		@SuppressWarnings("unchecked")
			public Set<Order> findByCustomer(Customer customer) {
			return new HashSet<Order>(getHibernateTemplate().find("from Orders where customers=?", customer));
		}
		 
		public void save(Order order) {
			getHibernateTemplate().save(order); 
		}

		public Order delete(Order order) {
			getHibernateTemplate().delete(order);
			return order;
		}


	
}

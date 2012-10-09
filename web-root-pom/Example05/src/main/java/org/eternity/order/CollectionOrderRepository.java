package org.eternity.order;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eternity.common.EntryPoint;
import org.eternity.common.Registrar;
import org.eternity.customer.Customer;

public class CollectionOrderRepository implements OrderRepository{

	private Registrar registrar;

	public void setRegistrar(Registrar registrar) {
		this.registrar = registrar;            
	}
	
	public CollectionOrderRepository() {

	}

	public void save(Order order) {
		registrar.add(Order.class, (Order)order);
	}
	 
	public Order find(String identity) {
	       return (Order)registrar.get(Order.class, identity);
	}

	public Set<Order> findByCustomer(Customer customer) {
		Set<Order> results = new HashSet<Order>();
		            
		for(Order order : findAll()) {
		if (order.isOrderedBy(customer)) {
		           results.add(order);
		       }
		}
		            
		return results;
	}
	
	public Set<Order> findAll() {
		return new HashSet<Order>((Collection<Order>)registrar.getAll(Order.class));
	}
	
	public Order delete(Order order) {
		return (Order)registrar.delete(Order.class, ((EntryPoint)order).getIdentity());
	}
	

}

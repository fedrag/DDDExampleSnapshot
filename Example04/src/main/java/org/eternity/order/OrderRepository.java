package org.eternity.order;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eternity.common.Registrar;
import org.eternity.customer.Customer;

public class OrderRepository {
	public void save(Order order) {
		Registrar.add(Order.class, order);
	}

	public Order find(String identity) {
		return (Order) Registrar.get(Order.class, identity);
	}

	public Set<Order> findByCustomer(Customer customer) {
		Set<Order> results = new HashSet<Order>();

		for (Order order : findAll()) {
			if (order.isOrderedBy(customer)) {
				results.add(order);
			}
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public Set<Order> findAll() {
		return new HashSet<Order>(
				(Collection<Order>) Registrar.getAll(Order.class));
	}
	
	/*실습 4-1 추가 코드*/
//	public Order delete(String identity) {
//		return (Order)Registrar.delete(Order.class, identity);
//	}
}

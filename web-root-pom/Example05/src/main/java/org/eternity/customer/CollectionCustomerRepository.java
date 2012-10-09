package org.eternity.customer;

import org.eternity.common.Registrar;

public class CollectionCustomerRepository implements CustomerRepository{
	private Registrar registrar;

	public void setRegistrar(Registrar registrar) {
		this.registrar = registrar;            
	}

	public CollectionCustomerRepository() {
	}

	public void save(Customer customer) {
		registrar.add(Customer.class, (Customer)customer);
	}
	 
	public Customer find(String identity) {
	    return (Customer)registrar.get(Customer.class, identity);
	}

}

package org.eternity.product;

import org.eternity.common.Registrar;

public class CollectionProductRepository implements ProductRepository{
	private Registrar registrar;

	public void setRegistrar(Registrar registrar) {
		this.registrar = registrar;            
	}
	
	public CollectionProductRepository() {
	}
	

	public void save(Product product) {
		registrar.add(Product.class, (Product)product);
	}
	 
	public Product find(String identity) {
	    return (Product)registrar.get(Product.class, identity);
	}

}

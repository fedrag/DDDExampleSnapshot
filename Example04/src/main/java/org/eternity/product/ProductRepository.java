package org.eternity.product;

import org.eternity.common.Registrar;

public class ProductRepository {
	public void save(Product product) {
	       Registrar.add(Product.class, product);
	}
	 
	public Product find(String identity) {
	       return (Product)Registrar.get(Product.class, identity);
	}
}

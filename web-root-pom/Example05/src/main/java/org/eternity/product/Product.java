package org.eternity.product;

import org.eternity.common.EntryPoint;
import org.eternity.common.Money;

public class Product extends EntryPoint{
	private String name;
	private Money price;


	public Product(String name, long price) {
		super(name);
		this.price = new Money(price);
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	public Product(String name, Money price) {
		super(name);
		this.price = price;
		this.name = name;
	}

	public Money getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

}

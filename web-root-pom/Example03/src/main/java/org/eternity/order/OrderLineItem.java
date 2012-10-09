package org.eternity.order;

import org.eternity.common.Money;
import org.eternity.product.Product;
import org.eternity.product.ProductRepository;

public class OrderLineItem {
	private Product product;
	private int quantity;

	private ProductRepository productRepository = new ProductRepository();

	public OrderLineItem(String productName, int quantity) {
		this.product = productRepository.find(productName);
		this.quantity = quantity;
	}

	public Money getPrice() {
		return product.getPrice().multiply(quantity);
	}

	public Product getProduct() {
		return product;
	}

//	public boolean isProductEqual(OrderLineItem lineItem) {
//		return product == lineItem.product;
//	}
//
//	public OrderLineItem merge(OrderLineItem lineItem) {
//		quantity += lineItem.quantity;
//		return this;
//	}
}

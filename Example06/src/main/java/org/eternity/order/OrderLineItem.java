package org.eternity.order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.eternity.common.Money;
import org.eternity.product.Product;
import org.eternity.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(value="orderLineItem",preConstruction=true)
@Entity
@Table(name="T_OrderLineItem")
public class OrderLineItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Product product;
	private int quantity;
	
	@Transient
	private ProductRepository productRepository;
	
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public OrderLineItem() {
		this.product = null;
		this.quantity = 0;
	}

	public OrderLineItem(String productName, int quantity) {
		this.product = (Product)productRepository.find(productName);
		this.quantity = quantity;
	}

	public Money getPrice() {
		return product.getPrice().multiply(quantity);
	}

	public Product getProduct() {
		return product;
	}

	public boolean isProductEqual(OrderLineItem lineItem) {
		return (product.getName().equals(lineItem.getProduct().getName()));
	}

	public OrderLineItem merge(OrderLineItem lineItem) {
		// TODO Auto-generated method stub
		this.quantity += lineItem.quantity;
		return this;
	}
	
	int getQuantity() {
	    return quantity;
	}
	
	public long getId() {
		return id;
	}

	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }
	 
	    if (!(object instanceof OrderLineItem)) {
	      return false;
	    }
	 
	    final OrderLineItem other = (OrderLineItem)object;
	    return this.product.equals(other.getProduct())
	      && this.quantity == other.getQuantity();
	}
	 
	public int hashCode() {
	    int result = 17;
	    result = 37*result + product.hashCode();
	    result = 37*result + quantity;            
	    return result;
	}


}

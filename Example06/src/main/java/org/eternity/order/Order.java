package org.eternity.order;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eternity.common.Money;
import org.eternity.customer.Customer;

@Entity
@Table(name="T_ORDER")
public class Order{
	@Id
	private String id;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ORDER_ID")
	private Set<OrderLineItem> lineItems = new HashSet<OrderLineItem>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Customer customer;
	
	public static Order order(String orderId, Customer customer) {
	    return new Order(orderId, customer);
	}
	public Order() {
		this.id = "";
	    this.customer = null;
	}
	public Order(String orderId, Customer customer) {
		this.id = orderId;
	    this.customer = (Customer)customer;
	}
	
	public Order with(String productName, int quantity) throws OrderLimitExceededException {
		  return with(new OrderLineItem(productName, quantity));
	}

	private Order with(OrderLineItem lineItem)  throws OrderLimitExceededException {
	  if (isExceedLimit(customer, lineItem)) {
	    throw new OrderLimitExceededException(OrderLimitExceededException.Order_Limit_Exceeded);
	  }
	  
	  for(OrderLineItem item : lineItems) {
		  if (item.isProductEqual(lineItem)) {
			  item.merge(lineItem);
			  return this;
		  }
	  }

	  lineItems.add(lineItem);            
	  return this;
	}
	 
	private boolean isExceedLimit(Customer customer, OrderLineItem lineItem) {
	  return customer.isExceedLimitPrice(getPrice().add(lineItem.getPrice()));
	}
	
	public Money getPrice() {
		Money result = new Money(0);
		 
		for(OrderLineItem item : lineItems) {
			result = result.add(item.getPrice());
		}
		 
		return result;
	}

	public int getOrderLineItemSize() {		
		return lineItems.size();
	}
	
	public boolean isOrderedBy(Customer customer) {
		return this.customer == customer;
	}
	
	public String getId() {
		return id;
	}
	


	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }

	    if (!(object instanceof Order)) {
	      return false;
	    }

	    final Order other = (Order)object;
	    return this.id.equals(other.getId());
	}

	public int hashCode() {
	    return this.id.hashCode();
	}

}

package org.eternity.customer;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.eternity.common.Money;
import org.eternity.order.Order;

@Entity
@Table(name="T_CUSTOMER")
public class Customer{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String customer_number;
	private String name;
	private String address;
	
	@Embedded
	@Column(name="mileage")
	private Money mileage;
	@Embedded
	@Column(name="limitPrice", nullable=true)
	private Money limitPrice;
	
	public Customer() {
		this.customer_number = "";
		this.name = "";
		this.address = "";
		this.mileage = new Money(0);
		this.limitPrice = null;		
	}

	public Customer(String customerNumber, String name, String address) {
		this.customer_number = customerNumber;
		this.name = name;
		this.address = address;
		this.mileage = new Money(0);
		this.limitPrice = new Money(500);
		
	}
	
	public Customer(String customerNumber, String name, String address, long limitPrice) {
		this.customer_number = customerNumber;
		this.name = name;
		this.address = address;
		this.mileage = new Money(0);
		this.limitPrice = new Money(limitPrice);
	}
	public Order newOrder(String orderId){
		return Order.order(orderId, this);		
	}
	
	public boolean isExceedLimitPrice(Money money){
		return money.isGreaterThan(limitPrice);
	}


	public void purchase(long price) {
		mileage = mileage.add(new Money(new BigDecimal(price * 0.01)));
	}

	public boolean isPossibleToPayWithMileage(long price) {
		return mileage.isGreaterThan(new Money(price));
	}

	public boolean payWithMileage(long price) {
		if (!isPossibleToPayWithMileage(price)) {
			return false;
		}
		mileage.getAmount().subtract(new Money(price).getAmount());
		return true;
	}

	public Money getMileage() {
		return mileage;
	}
	
	public Long getId() {
		return id;
	}
	

	public String getCustomer_number() {
		return customer_number;
	}

	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }

	    if (!(object instanceof Order)) {
	      return false;
	    }

	    final Customer other = (Customer)object;
	    return this.customer_number.equals(other.getCustomer_number());
	}

	public int hashCode() {
	    return this.customer_number.hashCode();
	}
	
//	public static Customer find(String customerName) {
//		 return (Customer)Registrar.get(Customer.class, customerName);
//	}
//	
//	public Customer persist() {
//		  return (Customer)super.persist();
//	}

}

package org.eternity.product;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;

import org.eternity.common.Money;
import org.eternity.order.Order;
import org.springframework.stereotype.Component;

@Entity
@Table(name="T_PRODUCT")
public class Product{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	@Embedded
	private Money price;
	
	public Product() {
		this.price = null;
		this.name = null;
		// TODO Auto-generated constructor stub
	}

	public Product(String name, long price) {
		this.price = new Money(price);
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	public Product(String name, Money price) {
		this.price = price;
		this.name = name;
	}

	public Money getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	
	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }

	    if (!(object instanceof Order)) {
	      return false;
	    }

	    final Product other = (Product)object;
	    return this.name.equals(other.getName());
	}

	public int hashCode() {
	    return this.name.hashCode();
	}
	

}

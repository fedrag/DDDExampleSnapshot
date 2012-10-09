package org.eternity.customer;

import java.math.BigDecimal;

import org.eternity.common.EntryPoint;
import org.eternity.common.Money;
import org.eternity.common.Registrar;
import org.eternity.order.Order;

public class Customer extends EntryPoint {
	private String customerNumber;
	private String name;
	private String address;
	private Money mileage;
	private Money limitPrice;

	public Customer(String customerNumber, String name, String address,
			long limitPrice) {
		super(customerNumber);
		this.customerNumber = customerNumber;
		this.name = name;
		this.address = address;
		this.limitPrice = new Money(limitPrice);
		mileage = new Money(0);
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

	public Order newOrder(String orderId) {
		return Order.order(orderId, this);
	}

	public boolean isExceedLimitPrice(Money money) {
		return money.isGreaterThan(limitPrice);
	}
}

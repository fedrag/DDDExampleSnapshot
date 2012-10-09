package org.eternity.common;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Money {
	@Column(columnDefinition="BIGINT", updatable=false, insertable=false)
	private final BigDecimal amount;
	
	public Money() {
		this.amount = new BigDecimal(0);
	}
	public Money(BigDecimal amount) {
		this.amount = amount;
	}

	public Money(long amount) {
		this(new BigDecimal(amount));
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Money)) {
			return false;
		}

		return amount.equals(((Money) object).amount);
	}

	public int hashCode() {
		return amount.hashCode();
	}

	public Money add(Money added) {
		return new Money(this.amount.add(added.amount));
	}

	public String toString() {
		return amount.toString();
	}

	public boolean isGreaterThan(Money limitPrice) {
		if (this.amount.compareTo(limitPrice.amount) > 0)
			return true;
		else
			return false;
	}

	public Money multiply(int quantity) {
		return new Money(this.amount.multiply(new BigDecimal(quantity)));
	}
}

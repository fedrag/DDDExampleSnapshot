package org.eternity.common;

import java.math.BigDecimal;


/** VO(불변객체)만드는 방법
 *  객체를 변경하는 메소드(mutator)를 제공하지 않는다.
 *	재정의할 수 있는 메소드를 제공하지 않는다.
 *	모든 필드를 final로 만든다.
 *	모든 필드를 private으로 만든다.
 *	가변 객체를 참조하는 필드는 배타적으로 접근해야 한다. 
 **/
public class Money {
	private final BigDecimal amount;

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
	
	/*추가코드*/
	
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

package org.eternity.common;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MoneyTest extends TestCase{

	@Test
	public void testMehodAlaising() throws Exception {
		Money money = new Money(2000);
		doSomethingWithMoney(money);
		assertEquals(new Money(2000), money);
	}
	
	private void doSomethingWithMoney(final Money money) {
		  money.add(new Money(2000));
	}

}

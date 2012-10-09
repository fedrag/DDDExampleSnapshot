package org.eternity.product;

import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.eternity.common.Money;
import org.eternity.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@ContextConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductTest extends TestCase{

	@Test
	@Transactional
	public void testProductConstructor() {
		Product product = new Product("상품1", 1000);
		assertEquals("상품1",product.getName());
		assertEquals(new Money(1000),product.getPrice());
	}

}

package org.eternity.product;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.eternity.product.Product;
import org.eternity.product.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest extends TestCase{

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@Transactional
	public void testProductSave() throws Exception {
		Product saveProduct = new Product("상품1", 1000);
		productRepository.save(saveProduct);

		assertSame(saveProduct, productRepository.find("상품1"));
	}
}

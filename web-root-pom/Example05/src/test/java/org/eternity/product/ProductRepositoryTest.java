package org.eternity.product;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.eternity.common.Money;
import org.eternity.common.Registrar;
import org.eternity.customer.Customer;
import org.eternity.product.Product;
import org.eternity.product.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest extends
		AbstractDependencyInjectionSpringContextTests {
	@Autowired
	private Registrar registrar;
	
	@Autowired
	private ProductRepository productRepository;

	
	@Before
	public void onSetUp() throws Exception {
		registrar.init();
	}
	
	@Test
	public void testProductSave() throws Exception {
		Product saveProduct = new Product("상품1", 1000);
		productRepository.save(saveProduct);

		assertSame(saveProduct, productRepository.find("상품1"));
	}
}

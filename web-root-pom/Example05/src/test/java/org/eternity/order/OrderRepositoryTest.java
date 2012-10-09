package org.eternity.order;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.eternity.common.Money;
import org.eternity.common.Registrar;
import org.eternity.customer.Customer;
import org.eternity.product.CollectionProductRepository;
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
public class OrderRepositoryTest extends AbstractDependencyInjectionSpringContextTests {
	@Autowired
	private Registrar registrar;
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	private Customer customer;

	@Before
	public void onSetUp() throws Exception {
		registrar.init();
		productRepository.save(new Product("상품1", 1000));
		productRepository.save(new Product("상품2", 5000));

		customer = new Customer("CUST-01", "홍길동", "경기도 안양시", 200000);
	}
	
	
	@Test
	public void testOrdreCount() throws Exception {
		orderRepository.save(customer.newOrder("CUST-01-ORDER-01")
		                                   .with("상품1", 5)
		                                   .with("상품2", 20)
		                                   .with("상품1", 5));
		       orderRepository.save(customer.newOrder("CUST-01-ORDER-02")
		                                   .with("상품1", 20)
		                                   .with("상품2", 5));
		            
		       assertEquals(2, orderRepository.findByCustomer(customer).size());
	}



}

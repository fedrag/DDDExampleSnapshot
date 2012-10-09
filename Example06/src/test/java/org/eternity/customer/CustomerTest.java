package org.eternity.customer;

import static org.junit.Assert.*;
import junit.framework.TestCase;

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
public class CustomerTest extends TestCase{

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@Transactional
	public void testAliasing() {
		  Customer customer = new Customer("CUST-01", "홍길동", "경기도 안양시", 200000);
		  Customer anotherCustomer = customer;
		 
		  long price = 1000;
		  customer.purchase(price);
		 
		  assertEquals(price*0.01, customer.getMileage().getAmount().doubleValue(), 0.1);
	}
	@Test
	@Transactional      
	public void testCustomerIdentical() {
		 Customer customer = new Customer("CUST-01", "홍길동", "경기도 안양시", 200000);
		 customerRepository.save(customer);
		 Customer anotherCustomer = customerRepository.find("CUST-01");
		 assertSame(customer, anotherCustomer);           
	}

}

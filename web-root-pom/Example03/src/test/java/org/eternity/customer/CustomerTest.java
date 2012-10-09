package org.eternity.customer;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.eternity.common.Registrar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerTest extends TestCase{
	
	@Before
	public void setUp() {
		Registrar.init();
	}

	@Test
	public void testAliasing() {
		  Customer customer = new Customer("CUST-01", "홍길동", "경기도 안양시", 200000);
		  Customer anotherCustomer = customer;
		 
		  long price = 1000;
		  customer.purchase(price);
		 
		  assertEquals(price*0.01, customer.getMileage().getAmount().doubleValue(), 0.1);
//		  assertEquals(0, anotherCustomer.getMileage());
	}
	
	/*수정 코드 */
	@Test	      
	public void testCustomerIdentical() {
		 CustomerRepository customerRepository = new CustomerRepository();    
		 Customer customer = new Customer("CUST-01", "홍길동", "경기도 안양시", 200000);
		 customerRepository.save(customer);
		 Customer anotherCustomer = customerRepository.find("CUST-01");
		 assertSame(customer, anotherCustomer);           
	}

}

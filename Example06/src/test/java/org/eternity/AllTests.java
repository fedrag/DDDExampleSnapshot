package org.eternity;

import org.eternity.common.DBManager;
import org.eternity.common.MoneyTest;
import org.eternity.customer.CustomerTest;
import org.eternity.order.OrderRepositoryTest;
import org.eternity.order.OrderTest;
import org.eternity.product.ProductRepositoryTest;
import org.eternity.product.ProductTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({MoneyTest.class, CustomerTest.class, OrderTest.class, ProductTest.class, OrderRepositoryTest.class, ProductRepositoryTest.class})
public class AllTests{
	 @BeforeClass 
	 public static void setUpClass() {
		 DBManager.getInstance().startHsqlDb();
	 }
	 @AfterClass
	 public static void tearDownClass() {
		 DBManager.getInstance().stopHsqlDb();
	 }
}



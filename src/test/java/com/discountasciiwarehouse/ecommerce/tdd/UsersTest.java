package com.discountasciiwarehouse.ecommerce.tdd;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.discountasciiwarehouse.ecommerce.business.RecentPurchaseBusiness;
import com.discountasciiwarehouse.ecommerce.business.RecentPurchaseBusinessImpl;

@Component
public class UsersTest{

	@Autowired
	private RecentPurchaseBusiness recentPurchaseBusiness;
	
//	@Before
//	public void init(){
//		
//		ApplicationContext context = 
//		    	  new ClassPathXmlApplicationContext(new String[] {"Beans.xml"});
//
//		recentPurchaseBusiness = (RecentPurchaseBusiness) context
//					.getBean("recentPurchaseBusiness");
//		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//
//			      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
//			      obj.getMessage();
//			      context.registerShutdownHook();
//	}
	
	@Test 
	public void shouldGetUser(){
		RecentPurchaseBusiness business = new RecentPurchaseBusinessImpl();
				
				
		UserDTO userReturn = business.getUser("Eileen50");
		
		UserDTO userTest = new UserDTO();
		userTest.setUsername("Eileen50");
		userTest.setEmail("Eileen50@hotmail.com");
		
		assertThat(userReturn, is(userTest));
	}
}

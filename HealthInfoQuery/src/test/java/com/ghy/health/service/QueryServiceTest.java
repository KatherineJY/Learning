package com.ghy.health.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueryServiceTest {
	private ApplicationContext ctx = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		ctx = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testQueryService(){
		QueryService.getStudentInfo(41510060, "èªè¤");
	}
}

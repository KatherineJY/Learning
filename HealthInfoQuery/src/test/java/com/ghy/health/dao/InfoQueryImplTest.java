package com.ghy.health.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ghy.health.model.HealthInfo;

public class InfoQueryImplTest {
	private ApplicationContext ctx = null;
	private InfoQueryDAO infoQueryDAO = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		infoQueryDAO = (InfoQueryDAO)ctx.getBean("infoQueryDAO");
		System.out.println("setup");
	}
	
	@After
	public void teardown(){
		ctx = null;
		infoQueryDAO = null;
		System.out.println("teardown");
	}
	
	@Test
	public void testQuery(){
		HealthInfo healthInfo = infoQueryDAO.queryInfo(2011,41510060 , "èªè¤");
		System.out.println(healthInfo.getHeight());
		System.out.println(healthInfo.getWeight());
		System.out.println(healthInfo.getRunning800());
	}
}

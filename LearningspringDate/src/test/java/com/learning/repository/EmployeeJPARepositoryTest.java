package com.learning.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import com.learning.domain.Employee;


public class EmployeeJPARepositoryTest {
	
	private ApplicationContext ctx = null;
	private EmployeeJPARepository employeeJPARepository = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		employeeJPARepository = ctx.getBean(EmployeeJPARepository.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		ctx = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testFind(){
		Employee employee = employeeJPARepository.findOne(99);
		System.out.println(employee);
	}
}

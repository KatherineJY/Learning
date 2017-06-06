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


public class EmployeePagingAndSortingRepositoryTest {
	
	private ApplicationContext ctx = null;
	private EmployeePagingAndSortingRepository employeePagingAndSortingRepository = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		employeePagingAndSortingRepository = ctx.getBean(EmployeePagingAndSortingRepository.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		ctx = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testPage(){
		Pageable pageable = new PageRequest(0, 5);
		Page<Employee> page = employeePagingAndSortingRepository.findAll(pageable);
		System.out.println(page.getTotalPages());
		System.out.println(page.getTotalElements());
		System.out.println(page.getNumber());
		System.out.println(page.getContent());
		System.out.println(page.getNumberOfElements());
	}
	
	@Test
	public void testPageAndSort(){
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
		Sort sort = new Sort(order);
		Pageable pageable = new PageRequest(0, 5,sort);
		Page<Employee> page = employeePagingAndSortingRepository.findAll(pageable);
		System.out.println(page.getTotalPages());
		System.out.println(page.getTotalElements());
		System.out.println(page.getNumber());
		System.out.println(page.getContent());
		System.out.println(page.getNumberOfElements());
	}
}

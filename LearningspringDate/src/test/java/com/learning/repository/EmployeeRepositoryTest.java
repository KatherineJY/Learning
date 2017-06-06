package com.learning.repository;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.domain.Employee;
import com.learning.repository.EmployeeRepository;


public class EmployeeRepositoryTest {
	
	private ApplicationContext ctx = null;
	private EmployeeRepository employeeRepository = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		employeeRepository = ctx.getBean(EmployeeRepository.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		ctx = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testFindByName(){
		Employee employee = employeeRepository.findByName("zhangsan");
		System.out.println("id:"+employee.getId()+", name:"+employee.getName());
	}
	
	@Test
	public void testGetEmployeeMaxId(){
		Employee employee = employeeRepository.getEmployeeMaxId();
		System.out.println("id:"+employee.getId()+", name:"+employee.getName());
	}
	
	@Test
	public void testQueryParams1(){
		List<Employee> employees = employeeRepository.queryParams1("zhangsan",20);
		for(Employee employee:employees){
			System.out.println("id:"+employee.getId()+", name:"+employee.getName());
		}
	}
	
	@Test
	public void testQueryParams2(){
		List<Employee> employees = employeeRepository.queryParams2("zhangsan",20);
		for(Employee employee:employees){
			System.out.println("id:"+employee.getId()+", name:"+employee.getName());
		}
	}
	
	@Test
	public void testLike1(){
		List<Employee> employees = employeeRepository.queryLike1("ss");
		for(Employee employee:employees){
			System.out.println("id:"+employee.getId()+", name:"+employee.getName());
		}
	}
	
	@Test
	public void testGetCount(){
		System.out.println(employeeRepository.getCount());
	}
	
	@Test
	public void testUpdate(){
		employeeRepository.update(1, 55);
	}
}

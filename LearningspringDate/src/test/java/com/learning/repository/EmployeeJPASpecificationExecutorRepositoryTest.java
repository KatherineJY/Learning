package com.learning.repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.learning.domain.Employee;


public class EmployeeJPASpecificationExecutorRepositoryTest {
	
	private ApplicationContext ctx = null;
	private EmployeeJPASpecificationExecutorRepository employeeJPASpecificationExecutorRepository = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		employeeJPASpecificationExecutorRepository = ctx.getBean(EmployeeJPASpecificationExecutorRepository.class);
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		ctx = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testQuery(){
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
		Sort sort = new Sort(order);
		Pageable pageable = new PageRequest(0, 5,sort);
		
		Specification<Employee> specification = new Specification<Employee>() {

			@Override
			public Predicate toPredicate(Root<Employee> root, 
										CriteriaQuery<?> query, 
										CriteriaBuilder cb) {
				//root就是我们要查询的类型
				//query添加查询条件
				//cb构建predicate
				Path path = root.get("age");
				return cb.gt(path, 50);
			}
		};
		
		Page<Employee> page = employeeJPASpecificationExecutorRepository.findAll(specification, pageable);
		
		System.out.println(page.getTotalPages());
		System.out.println(page.getTotalElements());
		System.out.println(page.getNumber());
		System.out.println(page.getContent());
		System.out.println(page.getNumberOfElements());
	}
	
}

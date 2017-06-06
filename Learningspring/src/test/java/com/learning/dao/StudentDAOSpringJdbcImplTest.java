package com.learning.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.domain.Student;

public class StudentDAOSpringJdbcImplTest {
	private ApplicationContext ctx = null;
	private StudentDAO studentDAO = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("setup");
		studentDAO = (StudentDAO)ctx.getBean("studentDAO");
	}
	
	@After
	public void tearDown(){
		ctx = null;
		System.out.println("tearDown");
	}
	
	
	@Test
	public void testQuery(){
		List<Student> students = studentDAO.query();
		
		for(Student student:students){
			System.out.println("id:"+student.getId()+", name:"+student.getName()+", age:"+student.getAge());
		}
	}
	
	@Test
	public void testSave(){
		Student student = new Student();
		student.setName("testt");
		student.setAge(32);
		studentDAO.save(student);
	}
}

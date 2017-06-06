package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.domain.Employee;
import com.learning.repository.EmployeeCrudRepository;
import com.learning.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
		
	@Autowired
	private EmployeeCrudRepository employeeCrudRepository;
	
	@Transactional
	public void update(Integer id,Integer age) {
		employeeRepository.update(id, age);
	}
	
	@Transactional
	public void save(List<Employee> employees){
		employeeCrudRepository.save(employees);
	}
}

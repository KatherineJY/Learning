package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.learning.domain.Employee;

public interface EmployeeJPASpecificationExecutorRepository 
	extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee>{
	
}

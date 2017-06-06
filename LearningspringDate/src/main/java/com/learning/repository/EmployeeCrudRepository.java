package com.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.learning.domain.Employee;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer>{

}

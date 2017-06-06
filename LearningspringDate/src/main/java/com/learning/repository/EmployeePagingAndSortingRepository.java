package com.learning.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.learning.domain.Employee;

public interface EmployeePagingAndSortingRepository extends PagingAndSortingRepository<Employee, Integer>{

}

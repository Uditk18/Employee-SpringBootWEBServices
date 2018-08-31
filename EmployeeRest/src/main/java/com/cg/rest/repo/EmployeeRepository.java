package com.cg.rest.repo;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.rest.pojo.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> 
{
	public Optional<Employee> findByEmpId(int id);
}

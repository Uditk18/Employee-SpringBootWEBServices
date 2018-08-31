package com.cg.rest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.rest.employeenotfound.EmployeeNotFoundException;
import com.cg.rest.pojo.Employee;
import com.cg.rest.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	@Autowired
	private EmployeeRepository repo;

	private Employee employee;

	@Override
	public List<Employee> viewAllEmployees() {
		return repo.findAll();
	}

	@Override
	public void addNewEmployee(Employee employee) {
		repo.save(employee);
	}

	public Employee getEmployeeById(int id) {
		
		employee = repo.findById(id).get();
		if (employee == null) 
		{// get fetches employee object from optional object
			throw new EmployeeNotFoundException("not found");
		} 
		else
			return employee;
	}

	@Override
	public void updateEmployee(Employee employee, int id) {
		repo.save(employee);
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employee = repo.findById(id).get();
		if(employee==null)
			throw new EmployeeNotFoundException("not found");
		else
			repo.deleteById(id);
	}

}

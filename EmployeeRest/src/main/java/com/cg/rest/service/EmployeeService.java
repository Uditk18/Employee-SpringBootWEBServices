package com.cg.rest.service;

import java.util.List;
import com.cg.rest.pojo.Employee;

public interface EmployeeService {

	List<Employee> viewAllEmployees();

	void addNewEmployee(Employee employee);

	void deleteEmployee(int id);

	void updateEmployee(Employee employee, int id);

	Employee getEmployeeById(int id);

}
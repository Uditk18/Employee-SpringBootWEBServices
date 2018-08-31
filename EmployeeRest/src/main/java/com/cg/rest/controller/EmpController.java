package com.cg.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
//Rest controller which handles all the dependencies and use annotations to perform actions
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rest.pojo.Employee;
import com.cg.rest.service.EmployeeService;

//Our program is a REST application and handles WEB Request
@CrossOrigin
@RestController
public class EmpController {

	

	@Autowired
	private EmployeeService empService;

	/*
	 * @RequestMapping It maps HTTP Requests to handler methods of MVC and REST
	 * controllers GIves Routing information Its like RequestDispatcher of spring
	 *
	 */

	// GET Method to show list of all employees
	@RequestMapping("/employee")
	public List<Employee> viewEmployee() {

		return empService.viewAllEmployees();
	}

	// POST Method to add new employee
	/*
	 * @RequestBody binds the HttpRequest with a domain object in method parameter
	 * or return type,enabling automatic deserialization of the inbound HttpRequest
	 * body onto the java object
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public void addNewEmployee(@RequestBody Employee employee) {
		empService.addNewEmployee(employee);
	}

	// GET method to get employee by id
	/*
	 * @PathVariable tells that we are passing a variable in the url
	 */
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Resource getEmployeeById(@PathVariable int id) {

		Link viewAll = linkTo(methodOn(this.getClass()).getEmployeeByPage(1, 9)).withRel("viewAll");

		Resource resource = new Resource(empService.getEmployeeById(id), viewAll);

		return resource;

	}

	// DELETE Method to delete an employee
	// {id} tells that id is a variable
	/*
	 * @PathVariable tells that we are passing a variable in the url
	 */

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable int id) {
		empService.deleteEmployee(id);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public void updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
		empService.updateEmployee(employee, id);
	}

	@RequestMapping(value = "/employee/{start}/{count}", method = RequestMethod.GET)
	public Resources getEmployeeByPage(@PathVariable int start, @PathVariable int count) {
		// Temporary list having all the employees
		List<Employee> tempEmployees = empService.viewAllEmployees();
		// Finals list storing final answers
		List<Employee> employees = new ArrayList<>();

		// Adding data to new arraylist between start and count from employee list temp
		for (int i = start; i < (start + count); i++) {
			employees.add(tempEmployees.get(i - 1));
		}

		// Now we have to add links to navigate to next 2 employee data
		Link nextLink = linkTo(methodOn(this.getClass()).getEmployeeByPage(start + count, count)).withRel("nextLink");
		Link prevLink = linkTo(methodOn(this.getClass()).getEmployeeByPage(start - count > 0 ? start - count : 1, count))
						.withRel("prevLink");
		Resources resources = new Resources(employees, nextLink, prevLink);

		return resources;

	}

}

package com.cg.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.cg.pojo.Employee;

@Controller
public class EmployeeController {
	@Autowired

	// Collection<Employee> empList;
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/showEmployees", method = RequestMethod.GET)
	public String viewEmployee(Model model) {
		
		//We will store all the data coming from the service in a collection
		Collection<Map> response = (ArrayList<Map>) restTemplate.getForObject("http://localhost:8080/employee",Collection.class);
		System.out.println(response);
		
		ArrayList<Employee> allEmployee= new ArrayList<>();
		for (Map map : response) {
			allEmployee.add(new Employee(Integer.parseInt(map.get("empId").toString()), map.get("empName").toString()));
		}

		model.addAttribute("emp",allEmployee);
		return "showEmployees";
	}
	
	@RequestMapping(value = "/employee/{id}")
	public String getEmpById(@PathVariable(value = "id") int id, Model model)  {
		ResponseEntity<Employee> responseEmp = restTemplate.getForEntity("http://localhost:8080/employee/" + id,
				Employee.class);
		System.out.println(responseEmp.getBody());

		Employee emp = new Employee(responseEmp.getBody().getEmpId(),responseEmp.getBody().getEmpName());
		ArrayList<Employee> list = new ArrayList<>();
		list.add(emp);
		model.addAttribute("allTopic", list);

		return "viewTopics";

	}

}

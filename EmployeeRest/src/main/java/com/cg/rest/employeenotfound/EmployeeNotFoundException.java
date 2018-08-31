package com.cg.rest.employeenotfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Employee Not Found")
public class EmployeeNotFoundException extends RuntimeException {
	
	public EmployeeNotFoundException(String msg) {
		super(msg);
	}

}

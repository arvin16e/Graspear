package com.Projectmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projectmanagement.Entity.Employees;
import com.Projectmanagement.Service.Services;

@RestController
@RequestMapping("/controller")
public class ControllerClass {

	@Autowired
	private Services sc;
	
	@PostMapping("/createUser")
	public Object createUser(@RequestBody Employees emp) {
		System.out.println("Create User");
		return sc.createUser(emp);
	}
	
}

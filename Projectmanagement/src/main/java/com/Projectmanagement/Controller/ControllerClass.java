package com.Projectmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Projectmanagement.Entity.Employees;
import com.Projectmanagement.Service.Exceptions;

import com.Projectmanagement.Service.RegisterUserService;
import com.Projectmanagement.Service.Services;

import jakarta.servlet.http.Cookie;


@RestController
@RequestMapping("/controller")
public class ControllerClass {

	@Autowired
	private Services sc;
	
	@Autowired
	private RegisterUserService rsc;
	
	@PostMapping("/createUser")
	private Object createUser(@RequestBody Employees emp) throws Exceptions {
		System.out.println("controller Create User");
		return rsc.createUser(emp);
	}
	
	@GetMapping("/loginUser/{emp_id}/{password}")
	private Object loginUser(@PathVariable String emp_Id,@PathVariable String password ) {
		
		System.out.println("controller loginUser");
		String token=sc.employeeLogin(emp_Id,password);
		
		if(token==null) {
			return "Invalid credentials";
		}
		Cookie jwtCookie=new Cookie("jwt", token);
		
		jwtCookie.setHttpOnly(true);
		jwtCookie.setSecure(true);
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(1000*60*60*7);
		return null;
	}
	
	@Deprecated
	@PostMapping
	private void createProject() {
		
	}
	@Deprecated
	@GetMapping
	private void getAllProject() {
		
	}
	@Deprecated
	@DeleteMapping
	private void deleteProject() {
		
	}
	
	
}

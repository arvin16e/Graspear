package com.example.SpringBootBecent.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootBecent.Entity.Employee;
import com.example.SpringBootBecent.service.EmployeeService;

import jakarta.persistence.PostUpdate;

@RestController
@RequestMapping("/student")
public class Controller {
	
	@Autowired
	public EmployeeService empService;
	
	@GetMapping("/getStudent")
	public String  goat() {
		return "Hello";
	}
	
	@PostMapping("/create")
	public String createStudent(@RequestBody Employee emp) {
		return empService.createStudent(emp);
	}
	
	@GetMapping("/listAll")
	public List<Employee> listAll(@RequestParam(value="age",required=false) Set<Integer> age) {
		return empService.getAll(age);
	}
	
	@GetMapping("/list")
	public Employee list(@RequestParam int id) {
		return empService.getById(id);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam int id) {
		return empService.delete(id);
	}
	
	@PostMapping("/update")
	public String update(@RequestBody Employee emp) {
		return empService.update(emp);
	}
	
}

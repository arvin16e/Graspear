package com.example.UserLogin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserLogin.Entity.Information;
import com.example.UserLogin.Entity.PersonalDetails;
import com.example.UserLogin.Entity.UserDetails;
import com.example.UserLogin.Service.ServiceClass;

@RestController
@RequestMapping("/details")
public class UserController {
	
	@Autowired
	private ServiceClass sc;
	
	@PostMapping("/create")
	public String create(@RequestBody UserDetails user) {
		
		return sc.create(user);
	}
	
	@GetMapping("/read/{id}")
	public Optional<PersonalDetails> read(@PathVariable int id) {
		return sc.read(id);
	}
	
	@GetMapping("/read/{email}/{password}")
	public PersonalDetails readByEmail(@PathVariable String email,@PathVariable String password) {
		return sc.readByEmail(email,password);
	}
	
	@PostMapping("/input")
	public PersonalDetails input(@RequestBody Information info ) {
		return sc.input(info);
	}
	
	@GetMapping("/show")
	public List<PersonalDetails> show() {
		return sc.show();
	}
	
	@PostMapping("/update")
	public String update(@RequestBody UserDetails user,PersonalDetails pd) {
		return sc.update(user);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam int id) {
		return sc.delete(id);
	}
}

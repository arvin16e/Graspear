package com.example.UserAdminLogin.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserAdminLogin.Entity.Admin;
import com.example.UserAdminLogin.Entity.Personal;
import com.example.UserAdminLogin.Entity.User;
import com.example.UserAdminLogin.Repository.PersonalRepo;
import com.example.UserAdminLogin.Service.Services;


@RestController
@RequestMapping("/login")
public class Controller {

	@Autowired(required=true)
	private Services sc;
	
	@PostMapping("/createAdmin")
	public String createAdmin(@RequestBody Admin admin) {
		return sc.createAdminDetails(admin);
	}
	
	@GetMapping("/accessAdmin/{username}/{password}")
	public Iterable<User> accessAdmin(@PathVariable String username,@PathVariable String password) {
		
		return sc.loginAdmin(username,password);
	}
	
	@PostMapping("/createUser")
	public void createUser(@RequestBody User user) {
		sc.createUser(user);
	}
	
//	@PostMapping("/createPersonal")
//	public void createPersonal(@RequestBody Personal personal){
//		sc.createPersonalDetails(personal);
//	}
	
	@GetMapping("/readUser")
	public Object readUser(@PathVariable String username) {
		return sc.read(username);
	}
	
	@GetMapping("/userLogin/{username}/{password}")
	public Iterable<User> userLogin(@PathVariable String username, @PathVariable String password) {
		return sc.loginUser(username,password);
	}
	
	@PostMapping("/update")
	public String update(@RequestBody User user) {
		return sc.update(user);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam UUID uuid) {
		return sc.delete(uuid);
	}
	
}

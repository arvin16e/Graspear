package com.example.UserAdminLogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.UserAdminLogin.Repository.AdminRepo;
import com.example.UserAdminLogin.Repository.AdminRepoImplementation;
import com.example.UserAdminLogin.Repository.PersonalRepo;
import com.example.UserAdminLogin.Repository.PersonalRepoImplementation;
import com.example.UserAdminLogin.Repository.UserRepo;
import com.example.UserAdminLogin.Repository.UserRepoImplementation;

@Configuration
public class config {

	@Bean
	public UserRepo userrepo() {
		return new UserRepoImplementation();
	}
	
	@Bean
	public PersonalRepo personalrepo() {
		return new PersonalRepoImplementation();
	}
	
	@Bean
	public AdminRepo adminrepo() {
		return new AdminRepoImplementation();
	}
}

package com.Projectmanagement.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projectmanagement.Entity.Employees;
import com.Projectmanagement.Repository.EmployeeRepo;

@Service
public class Services {
	
	@Autowired
	private EmployeeRepo emp_repo;
//	
//	@Autowired
//	private JwtService jwtservice;

	public String employeeLogin(String emp_Id, String password) {
		
		System.out.println("employeeLogin");
		Optional<Employees> emp=emp_repo.findByEmp_IdAndPassword(emp_Id , password);
		if(emp!=null) {
//			return jwtservice.generateToken(emp_Id);
		}
		return "Failed to Login";
	}



}

package com.Projectmanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projectmanagement.Entity.Employees;
import com.Projectmanagement.Repository.EmployeeRepo;

@Service
public class Services {

	@Autowired
	private EmployeeRepo empRepo;

	public Object createUser(Employees emp) {
		
		return null;
	}
	
	
	
}

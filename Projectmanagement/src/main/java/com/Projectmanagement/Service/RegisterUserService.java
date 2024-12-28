package com.Projectmanagement.Service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Projectmanagement.Entity.Employees;
import com.Projectmanagement.Repository.EmployeeRepo;

@Service
public class RegisterUserService {

	
	@Autowired
	private EmployeeRepo empRepo;
	
	
	//private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public Object createUser(Employees emp) throws Exceptions {
		System.out.println("RegisterService createUser ");
		Iterable<Employees> employees=empRepo.findAll();
		
		if(employees != null) {
			for (Employees n:employees) {
				if(n.getId().equalsIgnoreCase(emp.getId()) ) {
					throw  new Exceptions("User Already Exists");
				}
			}
		}
		
		UUID uuid=null;
		boolean flag=true;
		while(flag) {
			uuid=UUID.randomUUID();
			boolean flag1=true;
			for(Employees n:employees) {
				 if((uuid.equals(n.getUuid()))) {
					 flag1=false;
					 break;
					 
				 }
			}
			if(flag1) {
				flag=false;
			}
			
		}
		
		emp.setUuid(uuid);
		//emp.setPassword(encoder.encode(emp.getPassword()));
		empRepo.save(emp);
	//	return "emp created";
		
		return null;
	}
}

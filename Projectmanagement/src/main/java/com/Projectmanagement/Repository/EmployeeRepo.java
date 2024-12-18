package com.Projectmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Projectmanagement.Entity.Employees;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Long>{
	
}

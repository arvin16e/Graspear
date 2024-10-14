package com.example.UserAdminLogin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.UserAdminLogin.Entity.Admin;

public interface AdminRepo extends CrudRepository<Admin, Integer>{

	Admin findByusernameAndpassword(String username, String password);

}

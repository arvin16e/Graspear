package com.example.UserLogin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.UserLogin.Entity.PersonalDetails;
import com.example.UserLogin.Entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer>{

	
}

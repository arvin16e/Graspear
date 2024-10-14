package com.example.UserLogin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserLogin.Entity.PersonalDetails;

public interface PersonalRepository extends JpaRepository<PersonalDetails, Integer>{

}

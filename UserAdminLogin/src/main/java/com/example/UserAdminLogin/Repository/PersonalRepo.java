package com.example.UserAdminLogin.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonalRepo extends CrudRepository<PersonalRepo, Integer> {

	public void deleteByuuid(UUID uuid);
	
	
}

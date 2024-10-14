package com.example.UserAdminLogin.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.UserAdminLogin.Entity.User;


public interface UserRepo extends CrudRepository<User, Integer> {

	public Optional<User> findByuuid(UUID uuid);

	public User findByemailAndpassword(String email, String password);

	public void deleteByuuid(UUID uuid);

	public User findByusernameAndpassword(String username, String password);

}

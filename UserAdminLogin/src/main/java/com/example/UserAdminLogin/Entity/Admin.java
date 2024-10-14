package com.example.UserAdminLogin.Entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ch.qos.logback.core.subst.Token.Type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="admindetails")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="uuid" , nullable = false)
	private UUID uuid;
	@Column(name="username" , nullable = false)
	private String username;
	@Column(name="email" , nullable = false)
	private String email;
	@Column(name="password" , nullable = false)
	private String password;
	
	
	
	@JsonManagedReference
	@OneToOne(mappedBy="admin" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private User user;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public UUID getUuid() {
		return uuid;
	}


	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	
}

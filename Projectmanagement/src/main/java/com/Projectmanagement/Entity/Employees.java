package com.Projectmanagement.Entity;

import java.util.Date;
import java.util.UUID;

import com.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="Employees")
public class Employees {

	@Id
	@Column(name="Emp_Id", nullable=false)
	private String emp_id;
	@Column(name="UUID", nullable=false,unique=true)
	private UUID uuid;
	@Column(name="Firstname", nullable=false)
	private String firstname;
	@Column(name="Lastname", nullable=false)
	private String lastname;
	@Column(name="Email", nullable=false,unique=true)
	private String email;
	@Column(name="Phone_number", nullable=false,unique=true)
	private Long phone_number;
	@Column(name = "Password", nullable=false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name="Role")
	private Role role;
	
	@Lob
	@Column(name="Image", nullable=false)
	private byte[] imageData;
	@Column(name="Created_At")
	private Date createdAt;
	
	
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getId() {
		return emp_id;
	}

	public void setId(String id) {
		this.emp_id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(Long phone_number) {
		this.phone_number = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

}

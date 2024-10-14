package com.example.UserAdminLogin.Entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ch.qos.logback.core.subst.Token.Type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "userdetails")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "uuid", unique = true, nullable = false)
	private UUID uuid;
	
	@Column(name = "username",unique=true, nullable = false)
	
	private String Username;
	@Column(name = "email",unique=true, nullable = false)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "employee_id", unique = true, nullable = false)
	private int employee_id;
	@Column(name = "createdDate", nullable = false)
	private LocalDate createdDate;
	@Column(name = "createdBy", nullable = false)
	private String createdBy;
	@Column(name = "ModifiedDate", nullable = false)
	private LocalDate ModifiedDate;
	@Column(name = "ModifiedBy", nullable = false)
	private String ModifiedBy;
	@Column(name = "Emergency Contact", nullable = false)
	private long emergencyContact;
	private Integer logincount=0;
	
	private int admiId;
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	

	@JsonManagedReference
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Personal personal;

	
	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

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
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
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

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public long getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(long emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	
/*	@PrePersist
	private void onCreate() {
		
		LocalDate currentDate= LocalDate.now();
		this.createdDate=currentDate;
	}
	
	@PostPersist
	public void onUpdate() {
		this.ModifiedDate=LocalDate.now();
	} */
}

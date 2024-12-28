package com.Projectmanagement.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Projects")
public class Projects {

	@Id
	@Column(name = "project_Id", nullable = false, unique = true)
	private String project_Id;
	@Column(name = "projet_Name", nullable = false)
	private String project_Name;
	@Column(name = "created_At", nullable = false)
	private Date created_At;
	@Column(name = "Description", nullable = false)
	private String description;

	@OneToMany(mappedBy="project",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Tasks> tasks=new ArrayList<>();
	
	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProject_Id() {
		return project_Id;
	}

	public void setProject_Id(String project_Id) {
		this.project_Id = project_Id;
	}

	public String getProjet_Name() {
		return project_Name;
	}

	public void setProjet_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

}

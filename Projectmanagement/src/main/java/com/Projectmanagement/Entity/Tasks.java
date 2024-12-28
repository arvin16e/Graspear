package com.Projectmanagement.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Tasks")
public class Tasks {
	@Id
	@Column(name = "Task_Id")
	private String task_Id;
	
	@ManyToOne
	@JoinColumn(name = "project_Id",nullable=false)
	private Projects project_Id;
	
	@Column(name = "Task_name")
	private String task_name;
	
	@Column(name = "Task_created_At")
	private Date task_created_At;
	
	@Column(name="Description")
	private String description;
	
	@OneToOne // (mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)   learn and use
	 private TaskSchedules schedule;

	
	public String getTask_Id() {
		return task_Id;
	}

	public void setTask_Id(String task_Id) {
		this.task_Id = task_Id;
	}

	public Projects getProject_Id() {
		return project_Id;
	}

	public void setProject_Id(Projects project_Id) {
		this.project_Id = project_Id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public Date getTask_created_At() {
		return task_created_At;
	}

	public void setTask_created_At(Date task_created_At) {
		this.task_created_At = task_created_At;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskSchedules getSchedule() {
		return schedule;
	}

	public void setSchedule(TaskSchedules schedule) {
		this.schedule = schedule;
	}

	
	
}

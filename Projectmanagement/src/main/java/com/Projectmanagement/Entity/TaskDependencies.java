package com.Projectmanagement.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TaskDependencies")
public class TaskDependencies {

	@Id
	@Column(name="Dependency_id")
	private int dependency_id;
	@Column(name="Task_Id")
	private String task_id;
	@Column(name="dependent_task_id")
	private String dependent_task_id;
	
	
	
	public int getDependency_id() {
		return dependency_id;
	}
	public void setDependency_id(int dependency_id) {
		this.dependency_id = dependency_id;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getDependent_task_id() {
		return dependent_task_id;
	}
	public void setDependent_task_id(String dependent_task_id) {
		this.dependent_task_id = dependent_task_id;
	}
	
	
}

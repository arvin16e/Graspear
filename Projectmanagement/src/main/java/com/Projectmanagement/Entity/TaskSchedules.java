package com.Projectmanagement.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaskSchedules")
public class TaskSchedules {

	@Column(name = " Schedule_Id")
	private int schedule_Id;
	@Column(name = " project_ID")
	private String project_Id;
	@Column(name = " Task_ID")
	private String task_Id;
	@Column(name = " Planned_Start")
	private Date planned_Start;
	@Column(name = "Actual_Start")
	private Date actual_Start;
	@Column(name = " Planned_End")
	private Date planned_End;
	@Column(name = " Actual_End")
	private Date actual_End;
	@Column(name = " Float_Time")
	private int float_Time;
	@Column(name = " Planned_Duration")
	private int planned_duration;
	@Column(name = " Actual_Duration")
	private int actual_duration;

	@Column(name = " Emp_id")
	private String emp_id;

	@Column(name = " Assigned_By")
	private String assigned_By;

	@Column(name = " Priority")
	private int priority;

	public int getSchedule_Id() {
		return schedule_Id;
	}

	public void setSchedule_Id(int schedule_Id) {
		this.schedule_Id = schedule_Id;
	}

	public String getProject_Id() {
		return project_Id;
	}

	public void setProject_Id(String project_Id) {
		this.project_Id = project_Id;
	}

	public String getTask_Id() {
		return task_Id;
	}

	public void setTask_Id(String task_Id) {
		this.task_Id = task_Id;
	}

	public Date getPlanned_Start() {
		return planned_Start;
	}

	public void setPlanned_Start(Date planned_Start) {
		this.planned_Start = planned_Start;
	}

	public Date getActual_Start() {
		return actual_Start;
	}

	public void setActual_Start(Date actual_Start) {
		this.actual_Start = actual_Start;
	}

	public Date getPlanned_End() {
		return planned_End;
	}

	public void setPlanned_End(Date planned_End) {
		this.planned_End = planned_End;
	}

	public Date getActual_End() {
		return actual_End;
	}

	public void setActual_End(Date actual_End) {
		this.actual_End = actual_End;
	}

	public int getFloat_Time() {
		return float_Time;
	}

	public void setFloat_Time(int float_Time) {
		this.float_Time = float_Time;
	}

	public int getPlanned_duration() {
		return planned_duration;
	}

	public void setPlanned_duration(int planned_duration) {
		this.planned_duration = planned_duration;
	}

	public int getActual_duration() {
		return actual_duration;
	}

	public void setActual_duration(int actual_duration) {
		this.actual_duration = actual_duration;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getAssigned_By() {
		return assigned_By;
	}

	public void setAssigned_By(String assigned_By) {
		this.assigned_By = assigned_By;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}

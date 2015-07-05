package com.task.rof.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author pawan
 *
 */
@Entity
@Table(name = "tasks")
public class Task {

	@Id
	private int taskId;
	private String taskName;
	private String priority;
	private String status;
	
	
	public Task() {
		// Not used now
	}
	
	public Task(int taskId, String taskName, String priority) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.priority = priority;
		this.status = "open";
	}
	
	public int getTaskId() {
		return taskId;
	}
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

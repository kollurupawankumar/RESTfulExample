package com.task.rof.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author pawan
 *
 */
@XmlRootElement
public class TaskJAXB {

	private int taskId;
	private String taskName;
	private String priority;
	private String status;
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
	
	public TaskJAXB getTaskJAXB(Task task){
		TaskJAXB taskJAXB = new TaskJAXB();
		taskJAXB.setTaskId(task.getTaskId());
		taskJAXB.setTaskName(task.getTaskName());
		taskJAXB.setPriority(task.getPriority());
		taskJAXB.setStatus(task.getStatus());
		return taskJAXB;
	}
}

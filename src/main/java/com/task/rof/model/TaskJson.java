package com.task.rof.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author pawan
 *
 */
@XmlRootElement
public class TaskJson {
	
	List<TaskJAXB> taskList = new ArrayList<TaskJAXB>();

	public List<TaskJAXB> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskJAXB> taskList) {
		this.taskList = taskList;
	}
	
	public List<TaskJAXB> generateJAXBObject(List<Task> taskModelList){
		for(Task task : taskModelList){
			TaskJAXB taskJAXB = new TaskJAXB();
			taskJAXB.setTaskId(task.getTaskId());
			taskJAXB.setTaskName(task.getTaskName());
			taskJAXB.setPriority(task.getPriority());
			taskJAXB.setStatus(task.getStatus());
			taskList.add(taskJAXB);
		}
		
		return taskList;
	}

}

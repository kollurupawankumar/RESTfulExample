package com.task.rof.dao;

import java.util.List;

import com.task.rof.model.Task;

/**
 * 
 * @author pawan
 *
 */
public interface ITaskDAO {
	
	public List<Task> getTaskListOnPriority(String priority);
	
	public boolean addTask(Task task);
	
	public Task addTask1(Task task);
	
	public boolean deleteTask(int id);
	
	public boolean markTaskAsCompleted(int id);

}

package com.task.rof.rest;
 
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.task.rof.dao.ITaskDAO;
import com.task.rof.dao.TaskDAOImpl;
import com.task.rof.model.Task;
import com.task.rof.model.TaskJAXB;
import com.task.rof.model.TaskJson;
 
/**
 * 
 * @author pawan
 *
 */
@Path("/task")
public class TaskService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/list/{priority}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTasksList(@PathParam("priority") String priority){
		ITaskDAO taskDAO = new TaskDAOImpl();
		List<Task> taskList = taskDAO.getTaskListOnPriority(priority);
		ResponseBuilder builder = Response.ok();
		if (taskList == null ){
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}else{
			TaskJson taskJson = new TaskJson();
			taskJson.generateJAXBObject(taskList);
			builder.entity(taskJson);
			builder.status(200);
		}
		return builder.build();
	}
	
	@POST
	@Path("/addTask")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTask(JSONObject inputJsonObject) throws JSONException{
		ITaskDAO taskDAO = new TaskDAOImpl();
		List<Task> taskList = taskDAO.getTaskListOnPriority("all");
	
		Task task = new Task(taskList.size()+1,(String) inputJsonObject.get("taskname"),
				(String) inputJsonObject.get("taskpriority"));
		task = taskDAO.addTask1(task);
		ResponseBuilder builder = Response.ok();
		if (task == null){
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}else{
			builder.status(200);
			builder.entity(new TaskJAXB().getTaskJAXB(task));
		}
		
		return builder.build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(JSONObject inputJsonObject) throws JSONException{
		ITaskDAO taskDAO = new TaskDAOImpl();
	    Integer taskId = (Integer) inputJsonObject.get("taskid");
	    boolean status = taskDAO.markTaskAsCompleted(taskId);
		ResponseBuilder builder = Response.ok();
		if (!status){
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}else{
			builder.status(200);
		}
		return builder.build();
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTask(JSONObject inputJsonObject) throws JSONException{
		ITaskDAO taskDAO = new TaskDAOImpl();
	    Integer taskId = (Integer) inputJsonObject.get("taskid");
	    boolean status = taskDAO.deleteTask(taskId);
		ResponseBuilder builder = Response.ok();
		if (!status){
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}else{
			builder.status(200);
		}
		return builder.build();
	}
 
}
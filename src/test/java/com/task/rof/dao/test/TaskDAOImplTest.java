package com.task.rof.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.task.rof.dao.TaskDAOImpl;
import com.task.rof.model.Task;
/**
 * 
 * @author pawan
 *
 */
public class TaskDAOImplTest {

	private TaskDAOImpl toTest;
	
	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;
	@Mock
	private Query query;
	@Mock
	private Transaction tx;
	@Mock 
	Task task;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		toTest = new TaskDAOImpl();
		toTest.setSessionFactory(sessionFactory);
		Mockito.when(sessionFactory.openSession()).thenReturn(session);
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(session.beginTransaction()).thenReturn(tx);
		
	}
	
	@Test
	public void getTaskListOnPriorityWithData(){
		List<Task> result = new ArrayList<Task>();
		result.add(task);
		Mockito.when(query.list()).thenReturn(result);
		List<Task> resp = toTest.getTaskListOnPriority("all");
		Assert.assertEquals(1, resp.size());
		
	}
	
	@Test
	public void getTaskListOnPriorityWithNoData(){
		List<Task> result = null;
		Mockito.when(query.list()).thenReturn(result);
		List<Task> resp = toTest.getTaskListOnPriority("all");
		Assert.assertEquals(0,resp.size());
		
	}
	
	@Test
	public void getTaskListOnPriorityWithSQLException(){
		List<Task> result = new ArrayList<Task>();
		result.add(task);
		Mockito.when(query.list()).thenThrow(new RuntimeException());
		List<Task> resp = toTest.getTaskListOnPriority("all");
		Assert.assertEquals(0,resp.size());
	}
	
	
	@Test
	public void addTaskValidCase(){
		boolean resp = toTest.addTask(task);
		Assert.assertTrue(resp);
	}
	
	@Test
	public void addTaskInValidCase(){
		Mockito.when(session.save(Mockito.any(Task.class))).thenThrow(new RuntimeException());
		boolean resp = toTest.addTask(task);
		Assert.assertFalse(resp);
	}
	
	@Test
	public void addTask1ValidCase(){
		Task resp = toTest.addTask1(task);
		Assert.assertNotNull(resp);
	}
	
	@Test
	public void addTask1InValidCase(){
		Mockito.when(session.save(Mockito.any(Task.class))).thenThrow(new RuntimeException());
		Task resp = toTest.addTask1(task);
		Assert.assertNull(resp);
	}
	
	@Test
	public void deleteTaskValidCase(){
		Mockito.when(session.get(Task.class, 1)).thenReturn(task);
		boolean resp = toTest.deleteTask(1);
		Assert.assertTrue(resp);
	}
	
	@Test
	public void deleteTaskInValidCase(){
		Mockito.when(session.get(Task.class, 1)).thenReturn(new RuntimeException());
		boolean resp = toTest.deleteTask(1);
		Assert.assertFalse(resp);
	}
	
	@Test 
	public void markStateAsCompletedValidCase(){
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setInteger(Mockito.anyString(), Mockito.anyInt())).thenReturn(query);
		boolean resp  = toTest.markTaskAsCompleted(1);
		Assert.assertTrue(resp);
	}
	
	@Test 
	public void markStateAsCompletedInValidCase(){
		Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setInteger(Mockito.anyString(), Mockito.anyInt())).thenThrow(new RuntimeException());
		boolean resp  = toTest.markTaskAsCompleted(1);
		Assert.assertFalse(resp);
	}
	
	
}

package com.task.rof.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.task.rof.model.Task;
/**
 * 
 * @author pawan
 *
 */
public class TaskDAOImpl extends BaseDAO implements ITaskDAO{

	private Session session = null;
	private Transaction tx=null;
	private SessionFactory sessionFactory = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTaskListOnPriority(String priority) {
		if (sessionFactory == null){
			sessionFactory = configureSessionFactory();
		}
		List<Task> result = new ArrayList<Task>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if ("all".equalsIgnoreCase(priority)){
				result = session.createQuery("from Task").list();
			}else{
				Query query =  session.createQuery("select t from Task t where t.priority = :priority");
				query.setString("priority", priority);
				result = query.list();
			}
			
			
			if (result == null){
				return new ArrayList<Task>();
			}
			session.flush();
			tx.commit();
			System.out.println("The number of rows are : "+result.size());
		} catch (Exception e) {
			System.out.println("Error occured in getting the list of tasks"+e);
			tx.rollback();
		}finally{
			if(session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public boolean addTask(Task task) {
		boolean isPersisted = true;
		if (sessionFactory == null){
			sessionFactory = configureSessionFactory();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(task);
			// Committing the change in the database.
			session.flush();
			tx.commit();
		} catch (Exception e) {
			System.out.println("Error occured in addTask :"+e);
			isPersisted = false;
			tx.rollback();
		}finally{
			if(session != null) {
				session.close();
			}
			
		}
		return isPersisted;
	}

	@Override
	public boolean deleteTask(int id) {
		boolean isDeleted = true;
		if (sessionFactory == null){
			sessionFactory = configureSessionFactory();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Task task =(Task) session.get(Task.class, id);
			session.delete(task);
			// Committing the change in the database.
			session.flush();
			tx.commit();
		} catch (Exception e) {
			System.out.println("Error occured in deleteTask :"+e);
			isDeleted = false;
			tx.rollback();
		}finally{
			if(session != null) {
				session.close();
			}
			
		}
		return isDeleted;
	}

	@Override
	public boolean markTaskAsCompleted(int id) {
		boolean isUpdated = true;
		if (sessionFactory == null){
			sessionFactory = configureSessionFactory();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("update Task t set t.status = 'completed' where id = :id" );
			query.setInteger("id", id);
			query.executeUpdate();
			// Committing the change in the database.
			session.flush();
			tx.commit();
		} catch (Exception e) {
			System.out.println("Error occured in markTaskAsCompleted :"+e);
			isUpdated = false;
			tx.rollback();
		}finally{
			if(session != null) {
				session.close();
			}
			
		}
		return isUpdated;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Task addTask1(Task task) {
		if (sessionFactory == null){
			sessionFactory = configureSessionFactory();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(task);
			// Committing the change in the database.
			session.flush();
			tx.commit();
			session.close();
			return task;
		} catch (Exception e) {
			System.out.println("Error occured in addTask :"+e);
			tx.rollback();
		}
		if(session != null)
				session.close();
		return null;
	}
	
	

}

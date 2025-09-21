package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.AppExceptions.DuplicateTaskExceptions;
import com.example.AppExceptions.TaskNotFoundException;

public class TaskServices {
	
	List<Task> tasks=new ArrayList<>();
	public void addTask(Task t) throws DuplicateTaskExceptions{
		boolean existes=tasks.stream().anyMatch(tas -> tas.getId() == t.getId());
		if(existes) {
			throw new DuplicateTaskExceptions("Task id : "+t.getId() +" already exists");
		}
		else {
			tasks.add(t);
		}
		System.out.println("Added");
	}
	
	public List<Task> getAllTasks(){
		return tasks;
	}
	
	public void getPendingTasks() {
		tasks.stream().filter(t -> !t.isCompleted()).forEach(System.out::println);
	}
	
	public Task getTaskById(int id) throws TaskNotFoundException{
		return tasks.stream().filter(t->t.getId()==id).findFirst().orElseThrow(()-> new TaskNotFoundException("Didn't found given id: "+id));
	}
	
	public void updateTask(int id,String title,String description,Boolean completed) throws AppExceptions{
		Task t=getTaskById(id);
		String titleToUpdate=(title != null)?title:t.getTitle();
		String descriptionToUpdate=(description != null)?description:t.getDescription();
		boolean completedToUpdate=(completed != null)?completed:t.isCompleted();
		tasks.add(new Task(id, titleToUpdate, descriptionToUpdate, completedToUpdate));
		tasks.remove(t);
		System.out.println("Updated");	
	}
	public void deleteTask(int id) throws TaskNotFoundException{
		Task t=getTaskById(id);
		tasks.remove(t);
		System.out.println("Removed");
	}

}

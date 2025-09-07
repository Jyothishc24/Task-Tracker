package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.AppExceptions.DuplicateTaskExceptions;
import com.example.AppExceptions.TaskNotFoundException;
import com.example.Task.task;

public class TaskServices {
	
	List<task> tasks=new ArrayList<>();
	public void addTask(task t) throws DuplicateTaskExceptions{
		boolean existes=tasks.stream().anyMatch(tas -> tas.id() == t.id());
		if(existes) {
			throw new DuplicateTaskExceptions("Task id : "+t.id() +" already exists");
		}
		else {
			tasks.add(t);
		}
		System.out.println("Added");
	}
	
	public void getAllTasks(){
		tasks.forEach(System.out::println);
	}
	
	public void getPendingTasks() {
		tasks.stream().filter(t -> !t.completed()).forEach(System.out::println);
	}
	
	public task getTaskById(int id) throws TaskNotFoundException{
		return tasks.stream().filter(t->t.id()==id).findFirst().orElseThrow(()-> new TaskNotFoundException("Didn't found given id: "+id));
	}
	
	public void updateTask(int id,String title,String description,Boolean completed) throws AppExceptions{
		task t=getTaskById(id);
		String titleToUpdate=(title != null)?title:t.title();
		String descriptionToUpdate=(description != null)?description:t.description();
		boolean completedToUpdate=(completed != null)?completed:t.completed();
		tasks.add(new task(id, titleToUpdate, descriptionToUpdate, completedToUpdate));
		tasks.remove(t);
		System.out.println("Updated");	
	}
	public void deleteTask(int id) throws TaskNotFoundException{
		task t=getTaskById(id);
		tasks.remove(t);
		System.out.println("Removed");
	}

}

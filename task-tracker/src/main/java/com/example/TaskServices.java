package com.example;

import java.util.ArrayList;
import java.util.List;

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
	}
	
	public void getAllTasks(){
		tasks.forEach(System.out::println);
	}
	
	public void getPendingTasks() {
		tasks.stream().filter(t -> !t.completed()).forEach(System.out::println);
	}

}

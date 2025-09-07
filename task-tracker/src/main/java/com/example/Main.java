package com.example;

import com.example.AppExceptions.DuplicateTaskExceptions;
import com.example.Task.task;

public class Main {

	public static void main(String[] args) throws AppExceptions {
		TaskServices ts=new TaskServices();
		
		task[] tasksToAdd= {
				new task(1,"Learn Gradle","know about gradle", false),
				new task(2,"Git push","to learn git", false),
				new task(3,"stream,filter,map","learn title words", true),
				new task(1,"stream,filter,map","learn title words", true)

		};
		for(task t:tasksToAdd) {
			try {
				ts.addTask(t);
			} catch (DuplicateTaskExceptions e) {
				e.printStackTrace();
			}
		}
		System.out.println("All Tasks:");
		ts.getAllTasks();
		System.out.println("Pending Tasks:");
		ts.getPendingTasks();
		ts.updateTask(1, null, null, true);
		System.out.println();
		ts.getAllTasks();
		System.out.println();
		ts.getPendingTasks();
		System.out.println();
		ts.deleteTask(2);

		
	}
}

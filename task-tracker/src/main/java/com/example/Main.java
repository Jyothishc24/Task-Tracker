package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Task.task;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
			System.out.println("All Tasks:");
			ts.getAllTasks();
			System.out.println("Pending Tasks:");
			ts.getPendingTasks();			
	}
}

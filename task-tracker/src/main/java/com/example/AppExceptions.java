package com.example;

public class AppExceptions extends Exception{
	public AppExceptions(String message) {
		super(message);
	}
	public static class DuplicateTaskExceptions extends AppExceptions{
		public DuplicateTaskExceptions(String message){
			super(message);
		}
	}
	public static class TaskNotFoundException extends AppExceptions{
		public TaskNotFoundException(String message) {
			super(message);
		}
	}
}

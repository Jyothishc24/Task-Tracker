package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.AppExceptions.DuplicateTaskExceptions;
import com.example.AppExceptions.TaskNotFoundException;

class TaskServicesTests {

    private TaskServices ts;

    @BeforeEach
    void setUp() {
        ts = new TaskServices();
    }

    @Test
    void testAddTask() throws DuplicateTaskExceptions {
        Task t1 = new Task(1, "Task 1", "Description 1", false);
        ts.addTask(t1);

        assertEquals(1, ts.tasks.size());
        assertEquals("Task 1", ts.tasks.get(0).getTitle());

        // Check adding duplicate
        DuplicateTaskExceptions exception = assertThrows(DuplicateTaskExceptions.class, () -> {
            ts.addTask(t1);
        });
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testGetTaskById() throws Exception {
        Task t1 = new Task(1, "Task 1", "Desc 1", false);
        ts.addTask(t1);

        Task result = ts.getTaskById(1);
        assertEquals("Task 1", result.getTitle());

        // Check not found
        assertThrows(TaskNotFoundException.class, () -> ts.getTaskById(2));
    }

    @Test
    void testUpdateTask() throws Exception {
    	Task t1 = new Task(1, "Task 1", "Desc 1", false);
        ts.addTask(t1);

        ts.updateTask(1, "Updated Task", null, true);

        Task updated = ts.getTaskById(1);
        assertEquals("Updated Task", updated.getTitle());
        assertEquals("Desc 1", updated.getDescription()); // unchanged
        assertTrue(updated.isCompleted());
    }

    @Test
    void testDeleteTask() throws Exception {
    	Task t1 = new Task(1, "Task 1", "Desc 1", false);
        ts.addTask(t1);

        ts.deleteTask(1);
        assertEquals(0, ts.tasks.size());

        // Check deleting non-existing task
        assertThrows(TaskNotFoundException.class, () -> ts.deleteTask(2));
    }

    @Test
    void testGetPendingTasks() throws Exception {
        ts.addTask(new Task(1, "Task 1", "Desc 1", false));
        ts.addTask(new Task(2, "Task 2", "Desc 2", true));
        ts.addTask(new Task(3, "Task 3", "Desc 3", false));

        List<Task> pending = ts.tasks.stream().filter(t -> !t.isCompleted()).toList();
        assertEquals(2, pending.size());
    }
}

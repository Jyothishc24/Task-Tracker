package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.AppExceptions.DuplicateTaskExceptions;
import com.example.AppExceptions.TaskNotFoundException;
import com.example.Task.task;

class TaskServicesTests {

    private TaskServices ts;

    @BeforeEach
    void setUp() {
        ts = new TaskServices();
    }

    @Test
    void testAddTask() throws DuplicateTaskExceptions {
        task t1 = new task(1, "Task 1", "Description 1", false);
        ts.addTask(t1);

        assertEquals(1, ts.tasks.size());
        assertEquals("Task 1", ts.tasks.get(0).title());

        // Check adding duplicate
        DuplicateTaskExceptions exception = assertThrows(DuplicateTaskExceptions.class, () -> {
            ts.addTask(t1);
        });
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testGetTaskById() throws Exception {
        task t1 = new task(1, "Task 1", "Desc 1", false);
        ts.addTask(t1);

        task result = ts.getTaskById(1);
        assertEquals("Task 1", result.title());

        // Check not found
        assertThrows(TaskNotFoundException.class, () -> ts.getTaskById(2));
    }

    @Test
    void testUpdateTask() throws Exception {
        task t1 = new task(1, "Task 1", "Desc 1", false);
        ts.addTask(t1);

        ts.updateTask(1, "Updated Task", null, true);

        task updated = ts.getTaskById(1);
        assertEquals("Updated Task", updated.title());
        assertEquals("Desc 1", updated.description()); // unchanged
        assertTrue(updated.completed());
    }

    @Test
    void testDeleteTask() throws Exception {
        task t1 = new task(1, "Task 1", "Desc 1", false);
        ts.addTask(t1);

        ts.deleteTask(1);
        assertEquals(0, ts.tasks.size());

        // Check deleting non-existing task
        assertThrows(TaskNotFoundException.class, () -> ts.deleteTask(2));
    }

    @Test
    void testGetPendingTasks() throws Exception {
        ts.addTask(new task(1, "Task 1", "Desc 1", false));
        ts.addTask(new task(2, "Task 2", "Desc 2", true));
        ts.addTask(new task(3, "Task 3", "Desc 3", false));

        List<task> pending = ts.tasks.stream().filter(t -> !t.completed()).toList();
        assertEquals(2, pending.size());
    }
}

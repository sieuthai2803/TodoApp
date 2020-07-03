package com.spring.sieu.todo.service;

import java.util.List;

import com.spring.sieu.todo.model.Task;

public interface TaskService {
	List<Task> getTasksByAccountId(int accountId);
	void addNewTaskByAccount(Task task);
	boolean removeTask(Task task);
	Task getTasksById(int id);
}

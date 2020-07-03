package com.spring.sieu.todo.service.Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sieu.todo.model.Task;
import com.spring.sieu.todo.repository.TaskRepository;
import com.spring.sieu.todo.service.TaskService;

@Service
public class TaskServiceImplement implements TaskService {

	@Autowired
	private TaskRepository repository;

	@Override
	public List<Task> getTasksByAccountId(int accountId) {
		List<Task> listTask = new ArrayList<Task>();
		listTask = repository.findByAccountId(accountId);
		return listTask;
	}

	@Override
	public void addNewTaskByAccount(Task task) {
		task.setStatus(1);
		repository.save(task);
	}

	@Override
	public boolean removeTask(Task task) {
		task.setStatus(2);
		boolean check = true;
		try {
			repository.save(task);
		} catch (Exception e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}

	@Override
	public Task getTasksById(int id) {
		Task task = repository.findById(id).get();
		return task;
	}

}

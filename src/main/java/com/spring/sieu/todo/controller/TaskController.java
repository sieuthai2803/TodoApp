package com.spring.sieu.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sieu.todo.model.Task;
import com.spring.sieu.todo.service.Implement.TaskServiceImplement;

@RestController
@RequestMapping(path = "/free")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
	@Autowired
	private TaskServiceImplement service;
	
	@GetMapping(value = "/getByAccount", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Task> getAllTaskByAccount (@RequestParam(value = "accountId" ) int accountId) {
		return service.getTasksByAccountId(accountId);
	}
	
	@PostMapping(value = "/addTaskByAccount")
	public String addTaskByAccount(@RequestBody Task task) {
		String msg = "";
		if (task != null) {
			service.addNewTaskByAccount(task);
			msg="Add Success";
		}
		else
			msg = "Failed";
		return msg;
	}
	
	@GetMapping(value = "/remove-task")
	public boolean removeTask(@RequestParam(value = "id") int id) {
		Task task = service.getTasksById(id);
		return service.removeTask(task);
	}
}

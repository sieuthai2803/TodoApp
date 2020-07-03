package com.spring.sieu.todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.sieu.todo.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{
	List<Task> findByAccountId(int accountId);
	List<Task> findByAccountIdAndStatus(int accountId, int status);
}

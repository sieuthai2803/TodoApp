package com.spring.sieu.todo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.sieu.todo.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {	
	Account findByUsername(String username);
	ArrayList<Account> findAll();
}

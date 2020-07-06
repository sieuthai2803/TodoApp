package com.spring.sieu.todo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.sieu.todo.model.Account;
import com.spring.sieu.todo.model.AccountDetail;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {	
	Account findByUsername(String username);
	ArrayList<Account> findAll();
	boolean existsByUsername(String username);
	AccountDetail findById(int id);
}

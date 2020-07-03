package com.spring.sieu.todo.service;

import java.util.ArrayList;

import com.spring.sieu.todo.model.Account;

public interface AccountService {
	Account loadAccountByUsername(String username);

	boolean createNewAccount(String username, String password);
	
	ArrayList<Account> getAllAccount();
}

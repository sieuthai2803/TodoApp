package com.spring.sieu.todo.service.Implement;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sieu.todo.model.Account;
import com.spring.sieu.todo.repository.AccountRepository;
import com.spring.sieu.todo.service.AccountService;

@Service
public class AccountServiceImplement implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Override
	public Account loadAccountByUsername(String username) {
		Account account = repository.findByUsername(username);
		return account;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean createNewAccount(String username, String password) {
		Account newAccount = new Account(username, password);
		String existedUsername = repository.findByUsername(username).getUsername().trim();
		if (existedUsername.isEmpty()) {
			repository.save(newAccount);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Account> getAllAccount() {
		ArrayList<Account> listFound = (ArrayList<Account>) repository.findAll();
		return listFound;
	}

}

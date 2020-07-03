package com.spring.sieu.todo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sieu.todo.model.Account;
import com.spring.sieu.todo.service.Implement.AccountServiceImplement;

@RestController
@RequestMapping(path = "/free")
public class AccountController {
	@Autowired
	private AccountServiceImplement service;

	@GetMapping(value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Account getAccountByUsername(@RequestParam(value = "username") String username) {
		return service.loadAccountByUsername(username);
	}

	@GetMapping(value = "/getAll")
	public ArrayList<Account> getAllAccount() {
		ArrayList<Account> listRS = service.getAllAccount();
		return listRS;
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)  //1
	public String registerNewAccount(@RequestBody Account account) {
		String msg = "";
		boolean isCreated = service.createNewAccount(account.getUsername(), account.getPassword());
		if(isCreated)
			msg = "Account created";
		else
			msg = "Username existed";
		return msg;
	}
}

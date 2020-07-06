package com.spring.sieu.todo.service.Implement;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sieu.todo.model.Account;
import com.spring.sieu.todo.model.AccountDetail;
import com.spring.sieu.todo.repository.AccountRepository;
import com.spring.sieu.todo.service.AccountService;

@Service
public class AccountServiceImplement implements AccountService, UserDetailsService {

	@Autowired
	private AccountRepository repository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public Account loadAccountByUsername(String username) {
		Account account = repository.findByUsername(username);
		return account;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean createNewAccount(String username, String password) {
		password = passwordEncoder.encode(password);
		Account newAccount = new Account(username, password);
//		newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		if (!repository.existsByUsername(username)) {
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

	@Override
	public AccountDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = repository.findByUsername(username);
		if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AccountDetail(account);
	}

	public AccountDetail loadAccountById(int id) {
		AccountDetail accountDetail = repository.findById(id);
		return accountDetail;
	}

}

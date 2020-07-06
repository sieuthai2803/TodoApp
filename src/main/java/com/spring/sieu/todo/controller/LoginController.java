package com.spring.sieu.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sieu.todo.jwt.JwtTokenProvider;
import com.spring.sieu.todo.model.AccountDetail;

@RestController
@RequestMapping("/free")
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtProvider;
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public String authenticateSignin(@RequestBody AccountDetail accountDetail) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						accountDetail.getUsername(),
						accountDetail.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken((AccountDetail) authentication.getPrincipal());
		return jwt;
	}
}

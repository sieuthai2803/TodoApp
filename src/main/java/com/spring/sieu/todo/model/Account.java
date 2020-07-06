package com.spring.sieu.todo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "fullname")
	private String fullName;
	

	@OneToMany(mappedBy="account")
	private Set<Task> listTask = new  HashSet<Task>(); 

	public Account() {
		super();
	}

	public Account(String username, String password) {
		super();
		this.id = 0;
		this.username = username;
		this.password = password;
		this.fullName = "";
	}

	public Account(int id, String username, String password, String fullName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}
	
}

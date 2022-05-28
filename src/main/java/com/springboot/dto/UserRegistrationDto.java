package com.springboot.dto;

import java.util.List;

public class UserRegistrationDto {
	private int id;
	private String username;
	private String password;
	private String email;
	private List<Integer> roleid;
	
	public UserRegistrationDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRegistrationDto(int id, String username, String password, String email, List<Integer> roleid) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleid = roleid;
	}




	public List<Integer> getRoleid() {
		return roleid;
	}




	public void setRoleid(List<Integer> roleid) {
		this.roleid = roleid;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public UserRegistrationDto(String username,String email,String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.dto.UserRegistrationDto;
import com.springboot.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	List<User> getAllUser();
	void updateUser(User user);
	void removeUserById(int id);
	Optional<User> getUserById(int id);
}

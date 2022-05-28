package com.springboot.service;

import java.util.List;
import java.util.Optional;

import com.springboot.model.Role;

public interface RoleService {
	List<Role> getAllRole();
	Optional<Role> findRoleById(Integer id);
}

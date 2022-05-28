package com.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByName(String name);
}

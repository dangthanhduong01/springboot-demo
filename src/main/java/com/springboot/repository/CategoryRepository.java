package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}

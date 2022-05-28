package com.springboot.service;

import java.util.List;
import java.util.Optional;

import com.springboot.model.Category;



public interface CategoryService {
	public List<Category> getAllCategory();
	public void updateCategory(Category category);
    public void removeCategoryById(long id);
    public Optional<Category> getCategoryById(long id);	
}

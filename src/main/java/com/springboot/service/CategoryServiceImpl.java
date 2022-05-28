package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dto.MovieDTO;
import com.springboot.model.Category;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.MovieRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);
	}

	@Override
	public void removeCategoryById(long id) {
		// TODO Auto-generated method stub
		String i=Long.toString(id);
		movieRepository.deleteMovieByCategory_id(i);
		categoryRepository.deleteById(id);
		
	}

	@Override
	public Optional<Category> getCategoryById(long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

}

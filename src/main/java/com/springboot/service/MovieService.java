package com.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.model.Category;
import com.springboot.model.Movie;


public interface MovieService {
	List<Movie> getAllMovies();
	void saveMovie(Movie movie);
	void saveMovieToDB(Movie movie,MultipartFile file,String name,String des,String linkfilm ,Category category);
	Movie getMovieById(long id);
	void deleteMovieById(long id);
	List<Movie> getAllMovieByCategory_id(long id);
	List<Movie> findMovieByNameLike(String keyword);
	List<Movie> getAllMovieByHistory_id(long id);
	Movie findByMId(long id);

}

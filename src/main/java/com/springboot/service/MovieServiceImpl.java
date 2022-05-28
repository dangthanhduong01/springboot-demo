package com.springboot.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.model.Category;
import com.springboot.model.Movie;
import com.springboot.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CategoryService c;
	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	@Override
	public void saveMovie(Movie movie) {
		this.movieRepository.save(movie);
	}


	public void saveMovieToDB(Movie m,MultipartFile file, String name, String des,String linkfilm,Category category) {
//		if(m==null) {
//			m = new Movie();
//			}
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("Not valid");
		}
		try {
		m.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		if(des.isBlank() || name.isBlank()) {
			m.setDescription("N/a");
			m.setName("N/a");
		}else {
		m.setDescription(des);
		m.setName(name);
		m.setLinkFilm(linkfilm);
		}
		m.setCategory(category);
		this.movieRepository.save(m);
		
	}

	@Override
	public Movie getMovieById(long id) {
		Optional<Movie> optional=movieRepository.findById(id);
		Movie movie=null;
		if(optional.isPresent()) {
			movie=optional.get();
		}
		return movie;
	}

	@Override
	public void deleteMovieById(long id) {
		this.movieRepository.deleteById(id);
		
	}
	
	@Override
	public List<Movie> getAllMovieByCategory_id(long id) {
		return movieRepository.findAllByCategory_Id(id);
	}
	
	@Override
	public List<Movie> findMovieByNameLike(String keyword) {
		// TODO Auto-generated method stub
		if(keyword !=null) {
		return movieRepository.findByNameLike(keyword);
		}
		return movieRepository.findAll();
	}
	@Override
	public List<Movie> getAllMovieByHistory_id(long id) {
		// TODO Auto-generated method stub
		return movieRepository.findAllByHistory_Id(id);
	}
	@Override
	public Movie findByMId(long id) {		
		return movieRepository.getOne(id);
	}
	



}

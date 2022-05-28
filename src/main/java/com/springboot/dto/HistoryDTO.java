package com.springboot.dto;

import java.util.ArrayList;
import java.util.List;

import com.springboot.model.Movie;


public class HistoryDTO {
	private long id;
	private List<Movie> movie;
	private long user_id;
	
	public HistoryDTO() {
		movie = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Movie> getMovie() {
		return movie;
	}
	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public void addtoH(Movie m) {
		this.movie.add(m);
	}
	
}

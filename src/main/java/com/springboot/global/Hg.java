package com.springboot.global;

import java.util.ArrayList;
import java.util.List;

import com.springboot.model.Movie;

public class Hg {
	public static List<Movie> lmovie;
	
	static 
	{lmovie=new ArrayList<>();}

	public static List<Movie> getLmovie() {
		return lmovie;
	}

	public static void setLmovie(List<Movie> lmovie) {
		Hg.lmovie = lmovie;
	}
	
}

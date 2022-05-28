package com.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Movie;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long>{
	List<Movie> findAllByCategory_Id(long id);
	
	@Query(value="SELECT * FROM movies m WHERE m.name LIKE %?1%",nativeQuery = true)
	List<Movie> findByNameLike( String keyword);
	
	List<Movie> findAllByHistory_Id(long id);
	
	@Modifying
	@Query(value="delete FROM movies m WHERE m.category_id = ?1",nativeQuery = true)
	void deleteMovieByCategory_id(String id);
	
}

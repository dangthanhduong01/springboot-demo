package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.model.History;
import com.springboot.model.User;


@Repository
public interface HistoryRepository extends JpaRepository<History, Long>{

	@Query(value="SELECT * FROM history m WHERE m.user_id = ?1",nativeQuery = true)
	History findByUser_id(int id);
	
}

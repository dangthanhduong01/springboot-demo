package com.springboot.repository;







import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	
	@Modifying
    @Query(value="delete from user_role m WHERE m.userid = ?1 ",nativeQuery = true)
    void deleteur(  String id);	

}
package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bo.Auth;
import com.example.bo.User;

public interface AuthRepository extends JpaRepository<Auth, Integer> {
	
	@Query("select a FROM Auth a where a.email = :email AND a.token = :token")
	public Auth authenticateUser(@Param("email") String email,@Param("token") String token);

}

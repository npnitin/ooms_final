package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bo.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u FROM User u where u.email = :email AND u.password = :password")
	public User getUser(@Param("email") String email,@Param("password") String password);
	
	@Query("select u FROM User u where u.email = :email OR u.phone = :phone")
	public List<User> validateUserByEmailAndPhone(@Param("email") String email,@Param("phone") String phone);
}

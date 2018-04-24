package com.example.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import com.example.bo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c FROM Customer c where c.email = :email AND c.mobile = :mobile")
	public List<Customer> getCustomerByEmailAndMobile(@Param("email") String email,@Param("mobile") String mobile);

	public List<Customer> findByonboardDateBetween(Date start,Date end);
}

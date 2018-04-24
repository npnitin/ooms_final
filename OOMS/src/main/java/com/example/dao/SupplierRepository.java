package com.example.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bo.Customer;
import com.example.bo.Order;
import com.example.bo.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	
	public List<Supplier> findByonboardDateBetween(Date start,Date end);
	
	
	@Query("select s FROM Supplier s where s.email = :email AND s.phone = :phone")
	public List<Supplier> getSupplierByEmailAndMobile(@Param("email") String email,@Param("phone") String phone);
}

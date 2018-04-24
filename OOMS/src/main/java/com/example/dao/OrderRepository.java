package com.example.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	public List<Order> findBydateOfOrderBetween(Date start,Date end);
}

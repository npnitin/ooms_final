package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {

}

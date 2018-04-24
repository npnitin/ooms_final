package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}

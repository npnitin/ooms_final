package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.ProductItem;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {

}

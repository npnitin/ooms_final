package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.Tax;

public interface TaxRepository extends JpaRepository<Tax, Integer> {

}

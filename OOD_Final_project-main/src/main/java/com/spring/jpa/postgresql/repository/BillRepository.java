package com.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.postgresql.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{

}


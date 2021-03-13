package com.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.postgresql.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}


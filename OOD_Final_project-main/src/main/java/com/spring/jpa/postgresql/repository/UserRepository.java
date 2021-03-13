package com.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.postgresql.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
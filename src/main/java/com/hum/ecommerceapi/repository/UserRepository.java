package com.hum.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hum.ecommerceapi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailAndPassword(String email, String password);
}

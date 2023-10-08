package com.hum.ecommerceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hum.ecommerceapi.entity.Cart;
import com.hum.ecommerceapi.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	public List<Cart> findByUser(User user);
}

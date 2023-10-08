package com.hum.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hum.ecommerceapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

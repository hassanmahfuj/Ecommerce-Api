package com.hum.ecommerceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hum.ecommerceapi.entity.Product;
import com.hum.ecommerceapi.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<?> createUser(@Valid @RequestBody Product product) {
		return productService.createProduct(product);
	}

	@GetMapping("/get")
	public ResponseEntity<?> getProducts() {
		return productService.getProducts();
	}
}

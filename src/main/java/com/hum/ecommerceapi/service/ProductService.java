package com.hum.ecommerceapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hum.ecommerceapi.dto.Response;
import com.hum.ecommerceapi.entity.Product;
import com.hum.ecommerceapi.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<?> createProduct(Product product) {
		try {
			productRepository.save(product);
			return new ResponseEntity<Response>(new Response("success", "Product created successfully"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getProducts() {
		try {
			List<Product> list = productRepository.findAll();
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

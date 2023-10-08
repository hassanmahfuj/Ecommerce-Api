package com.hum.ecommerceapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hum.ecommerceapi.dto.Response;
import com.hum.ecommerceapi.entity.Cart;
import com.hum.ecommerceapi.entity.Product;
import com.hum.ecommerceapi.entity.User;
import com.hum.ecommerceapi.repository.CartRepository;
import com.hum.ecommerceapi.repository.ProductRepository;
import com.hum.ecommerceapi.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> addToCart(Integer userId, Integer productId, Integer quantity) {
		try {
			User user = userRepository.findById(userId).get();
			Product product = productRepository.findById(userId).get();

			Cart cart = new Cart();
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(quantity);

			cartRepository.save(cart);
			return new ResponseEntity<Response>(new Response("success", "Product added to cart"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getCartItems(Integer userId) {
		try {
			User user = userRepository.findById(userId).get();

			List<Cart> list = cartRepository.findByUser(user);
			return new ResponseEntity<List<Cart>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

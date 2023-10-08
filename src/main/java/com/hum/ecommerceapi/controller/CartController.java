package com.hum.ecommerceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hum.ecommerceapi.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@GetMapping("/add")
	public ResponseEntity<?> addToCart(@RequestParam Integer userId, @RequestParam Integer productId,
			@RequestParam Integer quantity) {
		return cartService.addToCart(userId, productId, quantity);
	}

	@GetMapping("/get")
	public ResponseEntity<?> getCartItems(@RequestParam Integer userId) {
		return cartService.getCartItems(userId);
	}
}

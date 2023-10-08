package com.hum.ecommerceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hum.ecommerceapi.dto.AuthRequest;
import com.hum.ecommerceapi.entity.User;
import com.hum.ecommerceapi.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@Valid @RequestBody AuthRequest authRequest) {
		return userService.authenticate(authRequest);
	}

}

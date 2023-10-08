package com.hum.ecommerceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hum.ecommerceapi.dto.AuthRequest;
import com.hum.ecommerceapi.dto.Response;
import com.hum.ecommerceapi.entity.User;
import com.hum.ecommerceapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> createUser(User user) {
		try {
			userRepository.save(user);
			return new ResponseEntity<Response>(new Response("success", "User registered successfully"),
					HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<Response>(new Response("error", "Email already registered"), HttpStatus.CONFLICT);
		}
	}

	public ResponseEntity<?> authenticate(AuthRequest authRequest) {
		try {
			User user = userRepository.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword());
			if(user != null) {
				return new ResponseEntity<Response>(new Response("success", "User authenticated"), HttpStatus.OK);				
			} else {
				return new ResponseEntity<Response>(new Response("error", "Email or Password is wrong"), HttpStatus.FORBIDDEN);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.CONFLICT);
		}
	}
}

package com.info.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.ecommerce.dto.UserRespnseDTO;
import com.info.ecommerce.entity.User;
import com.info.ecommerce.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping
	public ResponseEntity<UserRespnseDTO> createUser(@RequestBody User user){
		return ResponseEntity.ok(userService.registerUser(user));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticateUser(@RequestBody User user){
		return ResponseEntity.ok(userService.authenticateUser(user));
	}
	
}
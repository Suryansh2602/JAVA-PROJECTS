package com.info.urbaneats.user.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.urbaneats.user.JwtUtil;
import com.info.urbaneats.user.dto.UserDTO;
import com.info.urbaneats.user.entity.User;
import com.info.urbaneats.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService detailsService;
	private final JwtUtil jwt;

	public UserController(com.info.urbaneats.user.service.UserService userService,
			AuthenticationManager authenticationManager, UserDetailsService detailsService, JwtUtil jwt) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.detailsService = detailsService;
		this.jwt = jwt;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userService.isUserPresent(user)) {
			return ResponseEntity.badRequest().body("Email already exists");
		}
		return ResponseEntity.ok(userService.registerUser(user));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody User user) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		UserDetails userDetails = detailsService.loadUserByUsername(user.getEmail());
		String token = jwt.generateToken(userDetails);
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("message", "Sign in success");
		hm.put("token", token);
		return ResponseEntity.ok(hm);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		return ResponseEntity.ok(userService.softDeleteUser(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
	    return userService.updateUser(id, user);
	}

}

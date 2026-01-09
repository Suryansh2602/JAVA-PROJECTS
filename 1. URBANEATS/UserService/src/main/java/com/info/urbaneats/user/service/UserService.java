package com.info.urbaneats.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.urbaneats.restaurant.exception.ResourceNotFoundException;
import com.info.urbaneats.user.dto.UserDTO;
import com.info.urbaneats.user.entity.User;
import com.info.urbaneats.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;

	}

	@Transactional
	public ResponseEntity<?> registerUser(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		User dbUser = userRepo.save(user);
		UserDTO userdto = new UserDTO(dbUser);
		return ResponseEntity.ok(userdto);

	}
	
	public ResponseEntity<?> deleteUser(Integer id){
		User user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Username not found"));
	userRepo.delete(user);
	
		UserDTO userdto = new UserDTO(user);
		return ResponseEntity.ok(userdto);
		

	}

}

package com.info.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.dto.UserDTO;
import com.info.entity.User;
import com.info.repository.UserRepository;

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

}

package com.info.urbaneats.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
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

	public ResponseEntity<?> deleteUser(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Username not found"));
		userRepo.delete(user);

		UserDTO userdto = new UserDTO(user);
		return ResponseEntity.ok(userdto);

	}

	public ResponseEntity<?> softDeleteUser(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Username not found"));
		user.setStatus(false);
		User dbUser = userRepo.save(user);

		UserDTO userdto = new UserDTO(dbUser);
		return ResponseEntity.ok(userdto);

	}

	public ResponseEntity<UserDTO> getUser(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		UserDTO dto = new UserDTO(user);
		return ResponseEntity.ok(dto);

	}

	@Transactional
	public ResponseEntity<UserDTO> updateUser(Integer id, User user) {
		User dbuser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		if(!dbuser.getStatus()) {
			dbuser.setStatus(true);
		}

		if (user.getEmail() != null && !user.getEmail().equals(dbuser.getEmail())) {
			if (userRepo.findByEmail(user.getEmail()).isPresent()) {
				return ResponseEntity.badRequest().body(new UserDTO(dbuser));
			}
			dbuser.setEmail(user.getEmail());
		}

		if (user.getPhone() != null)
			dbuser.setPhone(user.getPhone());
		if (user.getUsername() != null)
			dbuser.setUsername(user.getUsername());
		if (user.getAdhar() != null)
			dbuser.setAdhar(user.getAdhar());
		if (user.getImage() != null)
			dbuser.setImage(user.getImage());

		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			dbuser.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		User updatedUser = userRepo.save(dbuser);
		return ResponseEntity.ok(new UserDTO(updatedUser));
	}

	public boolean isUserPresent(User user) {
		if (userRepo.findByEmail(user.getEmail()).isPresent())
			return true;
		return false;
	}

	
}

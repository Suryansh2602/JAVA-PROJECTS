package com.info.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.ecommerce.dto.UserRespnseDTO;
import com.info.ecommerce.entity.User;
import com.info.ecommerce.exception.UnAuthorisedException;
import com.info.ecommerce.repo.UserRepository;

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
	public UserRespnseDTO registerUser(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		User dbuser = userRepo.save(user);

		UserRespnseDTO userDTO = new UserRespnseDTO();
		userDTO.setEmail(dbuser.getEmail());
		userDTO.setId(dbuser.getId());
		userDTO.setUsername(dbuser.getUsername());
		return userDTO;

	}

	public User authenticateUser(User user) {
		User dbUser = userRepo.findByEmail(user.getEmail())
				.orElseThrow(() -> new UnAuthorisedException("Invalid Email ID"));
		if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			throw new UnAuthorisedException("Invalid Email Id");
		}
		return user;
	}

}
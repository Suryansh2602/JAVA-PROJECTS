package com.info.user.userservice;

import com.info.user.dto.UserResponsedto;
import com.info.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.info.user.entity.User;
@Service
public class UserService {

	private UserRepository userRepo;

	public UserService(UserRepository userRepo){
		this.userRepo = userRepo;
	}
	public ResponseEntity<UserResponsedto> saveUser(User user){
	UserResponsedto userdto = new UserResponsedto();

	}
}

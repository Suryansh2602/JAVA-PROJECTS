package com.info.urbaneats.user.dto;

import java.util.List;

import com.info.urbaneats.user.entity.Images;

import jakarta.persistence.OneToOne;

public class UserDTO {
	private Integer id;
	private String mail;
	private String phone;
	private List<String> restaurant;
	private Images image;
	
}

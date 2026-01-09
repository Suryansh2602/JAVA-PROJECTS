package com.info.urbaneats.user.dto;

import java.util.List;

import com.info.urbaneats.user.entity.Images;
import com.info.urbaneats.user.entity.User;

public class UserDTO {
	private Integer id;
	private String email;
	private String phone;
	private String username;
	private String role;
	private List<String> restaurant;
	private Images image;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.phone = user.getPhone();
		this.restaurant = user.getRestaurant();
		this.image = user.getImage();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(List<String> restaurant) {
		this.restaurant = restaurant;
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

}

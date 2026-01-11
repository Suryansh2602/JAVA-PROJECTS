package com.info.urbaneats.user.dto;

import java.util.List;

import com.info.urbaneats.user.entity.Images;
import com.info.urbaneats.user.entity.User;

public class UserDTO {
	private Integer id;
	private String username;
	private String role;
	private String email;
	private String adharMasked;
	private String phone;
	private Images image;
	
	public UserDTO() {}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.role = user.getRole();
		this.email = user.getEmail();

		this.adharMasked = maskAdhar(user.getAdhar());

		this.phone = user.getPhone();
		this.image = user.getImage();
	}

	public static UserDTO fromEntity(User user) {
		return new UserDTO(user);
	}

	private String maskAdhar(String adhar) {
		if (adhar == null || adhar.length() < 4) {
			return adhar != null ? adhar : "";
		}
		return "XXXXXXX" + adhar.substring(adhar.length() - 4);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdharMasked() {
		return adharMasked;
	}

	public void setAdharMasked(String adharMasked) {
		this.adharMasked = adharMasked;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}
}

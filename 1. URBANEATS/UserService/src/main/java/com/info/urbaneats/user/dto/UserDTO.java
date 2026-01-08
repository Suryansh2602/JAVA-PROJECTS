package com.info.urbaneats.user.dto;

import java.util.List;

import com.info.urbaneats.user.entity.Images;

public class UserDTO {
	private Integer id;
	private String mail;
	private String phone;
	private List<String> restaurant;
	private Images image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

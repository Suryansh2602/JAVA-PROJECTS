package com.info.urbaneats.user.dto;

import java.util.List;

public class RestaurantResponseDto {

	private Long id;
	private String name;
	private String description;
	private String ownerName;
	private String phone;
	private String whatsapp;
	private String mail;
	private String fssai;
	private Boolean isOpen;
	private List<String> cuisines;
	private List<String> images;

	public RestaurantResponseDto() {
	}

	public RestaurantResponseDto(Long id, String name, String description, String ownerName, String phone,
			String whatsapp, String mail, String fssai, Boolean isOpen, List<String> cuisines, List<String> images) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ownerName = ownerName;
		this.phone = phone;
		this.whatsapp = whatsapp;
		this.mail = mail;
		this.fssai = fssai;
		this.isOpen = isOpen;
		this.cuisines = cuisines;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFssai() {
		return fssai;
	}

	public void setFssai(String fssai) {
		this.fssai = fssai;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public List<String> getCuisines() {
		return cuisines;
	}

	public void setCuisines(List<String> cuisines) {
		this.cuisines = cuisines;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "RestaurantResponseDto{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
				+ ", ownerName='" + ownerName + '\'' + ", phone='" + phone + '\'' + ", whatsapp='" + whatsapp + '\''
				+ ", mail='" + mail + '\'' + ", fssai='" + fssai + '\'' + ", isOpen=" + isOpen + ", cuisines="
				+ cuisines + ", images=" + images + '}';
	}
}

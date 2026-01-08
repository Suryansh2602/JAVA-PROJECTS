package com.info.urbaneats.restaurant.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String fssai;
	private String phone;
	private String whatsapp;
	private String mail;
	private String password;
	private String ownerName;
	private String description;
	private List<String> cuisine;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<Images> images;
	
	

}

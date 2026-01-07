package com.info.urbaneats.restaurant.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Images {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private List<String> url;
@ManyToOne
private Restaurant restaurant;

}

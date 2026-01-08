package com.info.urbaneats.user.entity;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profile")
public class Images {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private List<String> url;
@OneToOne
private User user;

}

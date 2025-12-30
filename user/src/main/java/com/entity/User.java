package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String username;
private String email;
private String password;
}

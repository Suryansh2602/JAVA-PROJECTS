package com.info.ecommerce.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")

public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cart_id;

	@OneToOne
	@PrimaryKeyJoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "cart")
	private List<CartItems> itemList;

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItems> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItems> itemList) {
		this.itemList = itemList;
	}

}

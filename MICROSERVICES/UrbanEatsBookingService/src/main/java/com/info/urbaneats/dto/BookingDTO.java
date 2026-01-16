package com.info.urbaneats.dto;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.info.urbaneats.booking.entity.OrderStatus;

public class BookingDTO {

	private Integer id;

	private Integer userId;
	private Long restaurantId;

	private BigDecimal totalAmount;

	private String items;

	private OrderStatus status = OrderStatus.PENDING;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public BookingDTO() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public BookingDTO(Integer userId, Long restaurantId, BigDecimal totalAmount, String items, OrderStatus status) {
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalAmount = totalAmount;
		this.items = items;
		this.status = status != null ? status : OrderStatus.PENDING;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
		this.updatedAt = LocalDateTime.now();
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public String toString() {
		return "Order{" + "id=" + id + ", userId=" + userId + ", restaurantId=" + restaurantId + ", totalAmount="
				+ totalAmount + ", status=" + status + ", createdAt=" + createdAt + '}';
	}
}

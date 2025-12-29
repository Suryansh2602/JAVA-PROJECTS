package com.info.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.ecommerce.entity.Cart;
import com.info.ecommerce.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	Optional<Cart> findByUser(User user);
}

package com.info.urbaneats.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.urbaneats.restaurant.entity.Images;
import com.info.urbaneats.restaurant.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	List<Images> findImagesByRestaurant(Restaurant restaurant);
}

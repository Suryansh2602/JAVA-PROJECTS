package com.info.urbaneats.restaurant.controller;

import com.info.urbaneats.restaurant.dto.RestaurantDTO;
import com.info.urbaneats.restaurant.entity.Restaurant;
import com.info.urbaneats.restaurant.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

	private RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@PostMapping
	public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody Restaurant restaurant) {
		RestaurantDTO result = restaurantService.createRestaurant(restaurant);
		return ResponseEntity.status(201).body(result);
	}

	@GetMapping
	public ResponseEntity<Page<RestaurantDTO>> getAllRestaurants(@PageableDefault(size = 10) Pageable pageable) {
		Page<RestaurantDTO> restaurants = restaurantService.getAllRestaurants(pageable);
		return ResponseEntity.ok(restaurants);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Integer id) {
		RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
		return ResponseEntity.ok(restaurant);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Integer id,
			@RequestBody Restaurant updateData) {
		RestaurantDTO result = restaurantService.updateRestaurant(id, updateData);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer id) {
		restaurantService.deleteRestaurant(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/cuisine/{cuisineName}")
	public ResponseEntity<Page<RestaurantDTO>> getRestaurantsByCuisine(@PathVariable String cuisineName,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<RestaurantDTO> restaurants = restaurantService.getRestaurantsByCuisine(cuisineName, pageable);
		return ResponseEntity.ok(restaurants);
	}
}

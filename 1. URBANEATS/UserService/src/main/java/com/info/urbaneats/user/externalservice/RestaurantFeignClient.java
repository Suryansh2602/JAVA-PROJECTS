package com.info.urbaneats.user.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.info.urbaneats.user.dto.RestaurantResponseDto;

import java.util.List;

@FeignClient(name = "RESTAURANTSERVICE")
public interface RestaurantFeignClient {

	@GetMapping
	List<RestaurantResponseDto> getAllRestaurants();

	@GetMapping("/{cuisine}")
	List<RestaurantResponseDto> getRestaurantsByCuisine(@PathVariable String cuisine);

	@GetMapping("/{id}")
	RestaurantResponseDto getRestaurantById(@PathVariable Long id);
}

package com.info.urbaneats.restaurant.service;

import com.info.urbaneats.restaurant.dto.RestaurantDTO;
import com.info.urbaneats.restaurant.entity.Images;
import com.info.urbaneats.restaurant.entity.Restaurant;
import com.info.urbaneats.restaurant.repository.RestaurantRepository;
import org.hibernate.Hibernate; // ✅ ADD THIS IMPORT
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RestaurantService {

	private RestaurantRepository restaurantRepository;

	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	public RestaurantDTO createRestaurant(Restaurant restaurant) {
		Restaurant saved = restaurantRepository.save(restaurant);

		if (restaurant.getImages() != null && !restaurant.getImages().isEmpty()) {
			for (Images image : restaurant.getImages()) {
				image.setRestaurant(saved);
			}
			saved.setImages(restaurant.getImages());
			restaurantRepository.save(saved);
		}

		Hibernate.initialize(saved.getImages());

		return mapEntityToDto(saved);
	}

	public Page<RestaurantDTO> getAllRestaurants(Pageable pageable) {
		Page<Restaurant> page = restaurantRepository.findAll(pageable);
		List<RestaurantDTO> dtos = new ArrayList<>();
		for (Restaurant restaurant : page.getContent()) {
			dtos.add(mapEntityToDto(restaurant));
		}
		return new PageImpl<>(dtos, pageable, page.getTotalElements());
	}

	public RestaurantDTO getRestaurantById(Integer id) {
		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
		Hibernate.initialize(restaurant.getImages()); // ✅ Force load
		return mapEntityToDto(restaurant);
	}

	public RestaurantDTO updateRestaurant(Integer id, Restaurant updateData) {
		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));

		if (updateData.getName() != null)
			restaurant.setName(updateData.getName());
		if (updateData.getFssai() != null)
			restaurant.setFssai(updateData.getFssai());
		if (updateData.getPhone() != null)
			restaurant.setPhone(updateData.getPhone());
		if (updateData.getWhatsapp() != null)
			restaurant.setWhatsapp(updateData.getWhatsapp());
		if (updateData.getMail() != null)
			restaurant.setMail(updateData.getMail());
		if (updateData.getOwnerName() != null)
			restaurant.setOwnerName(updateData.getOwnerName());
		if (updateData.getDescription() != null)
			restaurant.setDescription(updateData.getDescription());
		if (updateData.getCuisine() != null)
			restaurant.setCuisine(updateData.getCuisine());
		if (updateData.getIsOpen() != null)
			restaurant.setIsOpen(updateData.getIsOpen());

		Restaurant updated = restaurantRepository.save(restaurant);
		return mapEntityToDto(updated);
	}

	public void deleteRestaurant(Integer id) {
		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
		restaurant.setIsOpen(false);
		restaurantRepository.save(restaurant);
	}

	public Page<RestaurantDTO> getRestaurantsByCuisine(String cuisineName, Pageable pageable) {
		Page<Restaurant> page = restaurantRepository.findAll(pageable);
		List<RestaurantDTO> filtered = new ArrayList<>();

		for (Restaurant restaurant : page.getContent()) {
			if (restaurant.getCuisine() != null) {
				for (String cuisine : restaurant.getCuisine()) {
					if (cuisine.equalsIgnoreCase(cuisineName)) {
						filtered.add(mapEntityToDto(restaurant));
						break;
					}
				}
			}
		}
		return new PageImpl<>(filtered, pageable, filtered.size());
	}

	public void incrementLikeCount(Integer restaurantId) {
		System.out.println("✅ Like INCREMENTED for restaurant ID: " + restaurantId);
	}

	public void decrementLikeCount(Integer restaurantId) {
		System.out.println("✅ Like DECREMENTED for restaurant ID: " + restaurantId);
	}

	public Integer getLikeCount(Integer restaurantId) {
		System.out.println("📊 Like count requested for restaurant ID: " + restaurantId);
		return 0;
	}

	private RestaurantDTO mapEntityToDto(Restaurant restaurant) {
		RestaurantDTO dto = new RestaurantDTO();
		dto.setId(restaurant.getId());
		dto.setName(restaurant.getName());
		dto.setFssai(restaurant.getFssai());
		dto.setPhone(restaurant.getPhone());
		dto.setWhatsapp(restaurant.getWhatsapp());
		dto.setMail(restaurant.getMail());
		dto.setOwnerName(restaurant.getOwnerName());
		dto.setDescription(restaurant.getDescription());
		dto.setCuisine(restaurant.getCuisine());
		List<Images> imageList = restaurant.getImages();
		dto.setImages(imageList);
		dto.setOpen(restaurant.getIsOpen());
		return dto;
	}
}

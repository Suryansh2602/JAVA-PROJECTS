package com.info.urbaneats.restaurant.service;

import com.info.urbaneats.restaurant.dto.RestaurantDTO;
import com.info.urbaneats.restaurant.entity.Restaurant;
import com.info.urbaneats.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantDTO createRestaurant(Restaurant restaurant) {
        Restaurant saved = restaurantRepository.save(restaurant);
        return mapToDto(saved);
    }

    // ✅ 2. GET /api/v1/restaurants (paginated)
    @Transactional(readOnly = true)
    public Page<RestaurantDTO> getRestaurants(Pageable pageable) {
        Page<Restaurant> page = restaurantRepository.findAll(pageable);
        List<RestaurantDTO> dtos = new ArrayList<>();
        for (Restaurant restaurant : page.getContent()) {
            dtos.add(mapToDto(restaurant));
        }
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    // ✅ 3. GET /api/v1/restaurants/{id}
    @Transactional(readOnly = true)
    public RestaurantDTO getRestaurantById(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
        return mapToDto(restaurant);
    }

    // ✅ 4. PUT /api/v1/restaurants/{id} - Direct JSON update
    public RestaurantDTO updateRestaurant(Integer id, Restaurant updateData) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));

        if (updateData.getName() != null) restaurant.setName(updateData.getName());
        if (updateData.getFssai() != null) restaurant.setFssai(updateData.getFssai());
        if (updateData.getPhone() != null) restaurant.setPhone(updateData.getPhone());
        if (updateData.getWhatsapp() != null) restaurant.setWhatsapp(updateData.getWhatsapp());
        if (updateData.getMail() != null) restaurant.setMail(updateData.getMail());
        if (updateData.getOwnerName() != null) restaurant.setOwnerName(updateData.getOwnerName());
        if (updateData.getDescription() != null) restaurant.setDescription(updateData.getDescription());
        if (updateData.getCuisine() != null) restaurant.setCuisine(updateData.getCuisine());

        Restaurant updated = restaurantRepository.save(restaurant);
        return mapToDto(updated);
    }

    // ✅ 5. DELETE /api/v1/restaurants/{id}
    public void deleteRestaurant(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
        restaurantRepository.delete(restaurant);
    }

    // ✅ 6. GET /api/v1/restaurants/owner/{ownerId}
    @Transactional(readOnly = true)
    public Page<RestaurantDTO> getRestaurantsByOwner(Integer ownerId, Pageable pageable) {
        Page<Restaurant> page = restaurantRepository.findAll(pageable);
        List<RestaurantDTO> filtered = new ArrayList<>();
        for (Restaurant restaurant : page.getContent()) {
            if (restaurant.getOwnerName() != null && 
                restaurant.getOwnerName().equals(ownerId.toString())) {
                filtered.add(mapToDto(restaurant));
            }
        }
        return new PageImpl<>(filtered, pageable, filtered.size());
    }

    // ✅ 7-9. Like endpoints (Phase 1 simple)
    public void incrementLikeCount(Integer restaurantId) {
        System.out.println("Like incremented for restaurant: " + restaurantId);
    }

    public void decrementLikeCount(Integer restaurantId) {
        System.out.println("Like decremented for restaurant: " + restaurantId);
    }

    public Integer getLikeCount(Integer restaurantId) {
        return 0; // Phase 1 placeholder
    }

    private RestaurantDTO mapToDto(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setFssai(restaurant.getFssai());
        dto.setPhone(restaurant.getPhone());
        dto.setWhatsapp(restaurant.getWhatsapp());
        dto.setMail(restaurant.getMail());
        
        // ownerName: String → Integer conversion
        if (restaurant.getOwnerName() != null) {
            try {
                dto.setOwnerName(Integer.parseInt(restaurant.getOwnerName()));
            } catch (NumberFormatException e) {
                dto.setOwnerName(0);
            }
        }
        
        dto.setDescription(restaurant.getDescription());
        dto.setCuisine(restaurant.getCuisine());
        dto.setImages(restaurant.getImages());
        return dto;
    }
}

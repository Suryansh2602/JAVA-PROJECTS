package com.info.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.info.ecommerce.dto.AddToCartRequestDTO;
import com.info.ecommerce.dto.CartResponseDTO;
import com.info.ecommerce.entity.Cart;
import com.info.ecommerce.entity.CartItems;
import com.info.ecommerce.entity.Product;
import com.info.ecommerce.entity.User;
import com.info.ecommerce.exception.ResourceNotFoundException;
import com.info.ecommerce.repo.CartItemRepository;
import com.info.ecommerce.repo.CartRepository;
import com.info.ecommerce.repo.ProductRepository;
import com.info.ecommerce.repo.UserRepository;

public class CartService {
	private CartRepository cartRepo;
	private UserRepository userRepo;
	private ProductRepository productRepo;
	private CartItemRepository cartItemRepo;

	public CartService(CartRepository cartRepo, UserRepository userRepo, ProductRepository productRepo,
			CartItemRepository cartItemRepo) {
		this.cartRepo = cartRepo;
		this.userRepo = userRepo;
		this.productRepo = productRepo;
		this.cartItemRepo = cartItemRepo;
	}

	public HashMap<String, String> addTocart(AddToCartRequestDTO cartdto) {
		User dbUser = userRepo.findById(cartdto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found Exception"));
		Product dbProduct = productRepo.findById(cartdto.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		Cart cart = cartRepo.findByUser(dbUser).orElseGet(() -> {
			Cart cart2 = new Cart();
			cart2.setUser(dbUser);
			return cartRepo.save(cart2);
		});

		Optional<CartItems> cartItems = cartItemRepo.findByCartAndProduct(cart, dbProduct);
		HashMap<String, String> hm = new HashMap<>();
		if (cartItems.isPresent()) {
			hm.put("message ", "Product is already in cart ");

		} else {
			CartItems cartItem = new CartItems();
			cartItem.setCart(cart);
			cartItem.setProduct(dbProduct);
			cartItemRepo.save(cartItem);
			hm.put("Message", "Item Successfully added in cart");
		}
		return hm;
	}

	public HashMap<String, List<CartResponseDTO>> fetchCartDetails(int userId) {
		User dbUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Cart cart = dbUser.getCart();
		List<CartResponseDTO> productList = new ArrayList<>();
		if (cart != null) {
			List<CartItems> itemList = cart.getItemList();
			for (CartItems item : itemList) {
				Product p = item.getProduct();
				CartResponseDTO dto = new CartResponseDTO();
				dto.setId(p.getId());
				dto.setTitle(p.getTitle());
				dto.setBrand(p.getBrand());
				dto.setPrice(p.getPrice());
				productList.add(dto);
			}
		}
		HashMap<String, List<CartResponseDTO>> hm = new HashMap<>();
		hm.put("cart-details", productList);
		return hm;
	}

}

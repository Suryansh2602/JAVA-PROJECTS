package com.info.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.info.ecommerce.dto.ProductResponseDTO;
import com.info.ecommerce.entity.Product;
import com.info.ecommerce.service.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {

	private ProductService productservice;

	public ProductController(ProductService productService) {
		this.productservice = productService;
	}

	@PostMapping("/{categoryId}")
	public ProductResponseDTO saveProduct(@PathVariable int categoryId, @RequestBody Product product) {
		return productservice.save(categoryId, product);
	}

	@GetMapping("/page")
	public ResponseEntity<List<ProductResponseDTO>> getProductPerPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(productservice.getProductPerPage(page, size));
	}

	@PostMapping("/category/{categoryId}/bulk")
	public ResponseEntity<List<ProductResponseDTO>> saveInBulk(@PathVariable int categoryId,
			@RequestBody List<Product> list) {
		List<ProductResponseDTO> dtoList = productservice.saveInBulk(categoryId, list);
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoList);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> getList() {
		return ResponseEntity.ok(productservice.getList());
	}
}

package com.info.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.info.dto.CategoryDTO;

@FeignClient(name = "category-service",
url = "https://dummyjson.com/products/categories")
public interface CategoryClient {
	@GetMapping
	List<CategoryDTO> getCategories();
	

}

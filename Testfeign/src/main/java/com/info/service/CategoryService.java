package com.info.service;

import com.info.repo.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	private CategoryRepo categoryRepo;

	public CategoryService(CategoryRepo categoryRepo){
		this.categoryRepo = categoryRepo;
	}
}

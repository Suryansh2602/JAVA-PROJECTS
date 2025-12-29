package com.info.springcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.springcrud.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}

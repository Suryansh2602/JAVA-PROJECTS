package com.info.urbaneats.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.urbaneats.restaurant.entity.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer>{

}

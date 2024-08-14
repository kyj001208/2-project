package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.ImageEntity;

public interface ImagesRepository extends JpaRepository<ImageEntity, Long>{

}

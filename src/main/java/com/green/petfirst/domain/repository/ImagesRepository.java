package com.green.petfirst.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.ImageEntity;

public interface ImagesRepository extends JpaRepository<ImageEntity, Long>{

	List<ImageEntity> findByProduct_ProductNo(long productNo);

}

package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.petfirst.domain.entity.ProductEntity;

@Repository
public interface Product_addRepository extends JpaRepository<ProductEntity, Long> {
}

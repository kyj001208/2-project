package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.CartProductEntity;

public interface CartRepository extends JpaRepository<CartProductEntity, Long>{

}

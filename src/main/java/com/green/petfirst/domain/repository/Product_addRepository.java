package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.petfirst.domain.entity.ProductEntity;

public interface Product_addRepository extends JpaRepository<ProductEntity, Long> {
    // 추가적인 쿼리 메소드 정의 가능
}
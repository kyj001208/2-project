package com.green.petfirst.domain.repository;

import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByProductNo(Long productNo);

    // 카테고리별 상품 검색 (CategoryEntity 객체 사용)
    List<ProductEntity> findByCategory(CategoryEntity category);

    // 가격 범위로 상품 검색
    List<ProductEntity> findByPriceBetween(Long minPrice, Long maxPrice);

    // 상품명으로 검색
    List<ProductEntity> findByProductNameContaining(String keyword);

    // 회원별 상품 검색
    List<ProductEntity> findByMemberMemNo(Long memNo);

    // 할인 상품 검색
    List<ProductEntity> findByDiscountGreaterThan(Long discount);

    // 재고 있는 상품 검색
    List<ProductEntity> findByQuantityGreaterThan(Long quantity);

    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.images WHERE p.productNo = :productNo")
    ProductEntity findByProductNoWithImages(@Param("productNo") Long productNo);
}
//package com.green.petfirst.domain.repository;
//
//
//import com.green.petfirst.domain.entity.ProductEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//
//    ProductEntity findByProductNo(Long productNo);
//
//    // 필요에 따라 추가적인 쿼리 메서드를 정의할 수 있습니다.
//    // 예: 카테고리별 상품 검색
//    List<ProductEntity> findByCategoryNo(String categoryNo);
//
//    // 가격 범위로 상품 검색
//    List<ProductEntity> findByPriceBetween(Double minPrice, Double maxPrice);
//
//    // 상품명으로 검색
//    List<ProductEntity> findByProductNameContaining(String keyword);
//}
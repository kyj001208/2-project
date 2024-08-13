package com.green.petfirst.service.product.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.petfirst.domain.dto.product.Product_addDTO;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.Product_addRepository;
import com.green.petfirst.service.product.Product_addService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Product_addServiceProcess implements Product_addService {

    private final Product_addRepository productRepository;

    @Override
    @Transactional
    public void addProduct(Product_addDTO productAddDTO) {
        // DTO를 Entity로 변환
        ProductEntity productEntity = ProductEntity.builder()
                .productName(productAddDTO.getProductName())
                .price(productAddDTO.getPrice())
                .productDetail(productAddDTO.getProductDetail())
                .quantity(productAddDTO.getQuantity())
                .discount(productAddDTO.getDiscount())
                .discountPrice(productAddDTO.getDiscountPrice())
                .category(productAddDTO.getCategory()) // 카테고리 설정
                .build();

        // 상품 엔티티 저장
        productRepository.save(productEntity);
    }
}
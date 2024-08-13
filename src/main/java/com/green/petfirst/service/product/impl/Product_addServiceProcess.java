package com.green.petfirst.service.product.impl;

import org.springframework.stereotype.Service;

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
    public Product_addDTO createProduct(Product_addDTO productDTO) {
        // DTO를 Entity로 변환
        ProductEntity productEntity = ProductEntity.builder()
                .category(productDTO.getCategory())
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .productDetail(productDTO.getProductDetail())
                .quantity(productDTO.getQuantity())
                .discount(productDTO.getDiscount())
                .discountPrice(productDTO.getDiscountPrice())
                .build();

        // DB에 저장
        ProductEntity savedProduct = productRepository.save(productEntity);

        // 저장된 Entity를 다시 DTO로 변환하여 반환
        return Product_addDTO.builder()
                .productNo(savedProduct.getProductNo())
                .category(savedProduct.getCategory())
                .productName(savedProduct.getProductName())
                .price(savedProduct.getPrice())
                .productDetail(savedProduct.getProductDetail())
                .quantity(savedProduct.getQuantity())
                .discount(savedProduct.getDiscount())
                .discountPrice(savedProduct.getDiscountPrice())
                .build();
    }
}
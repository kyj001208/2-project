package com.green.petfirst.service.product;

import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Transactional(readOnly = true)
    public ProductEntity getProductByNo(Long productNo) {
        return productRepository.findByProductNo(productNo);
    }

    public List<ProductEntity> getProductsByCategory(CategoryEntity category) {
        return productRepository.findByCategory(category);
    }

    public List<ProductEntity> getProductsByPriceRange(Long minPrice, Long maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<ProductEntity> searchProductsByName(String keyword) {
        return productRepository.findByProductNameContaining(keyword);
    }

    public List<ProductEntity> getProductsByMember(Long memNo) {
        return productRepository.findByMemberMemNo(memNo);
    }

    public List<ProductEntity> getDiscountedProducts(Long minDiscount) {
        return productRepository.findByDiscountGreaterThan(minDiscount);
    }

    public List<ProductEntity> getProductsInStock(Long minQuantity) {
        return productRepository.findByQuantityGreaterThan(minQuantity);
    }
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
}
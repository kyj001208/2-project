package com.green.petfirst.service.product;


import com.green.petfirst.domain.dto.product.Product_addDTO;

public interface Product_addService {
    Product_addDTO createProduct(Product_addDTO productDTO);
}
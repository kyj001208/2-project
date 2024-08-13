package com.green.petfirst.controller.Admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.petfirst.domain.dto.product.Product_addDTO;
import com.green.petfirst.service.product.Product_addService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product_add")
public class Product_addController {

    private final Product_addService productService;

    @PostMapping
    public ResponseEntity<Product_addDTO> createProduct(@RequestBody Product_addDTO productDTO) {
        Product_addDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }
}

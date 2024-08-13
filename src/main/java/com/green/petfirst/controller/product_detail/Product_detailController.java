package com.green.petfirst.controller.product_detail;

import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.service.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class Product_detailController {
    private final ProductService productService;

    @GetMapping("/petfir/product-detail")
    public String getProductDetail(@RequestParam(name = "productNo", required = false) Long productNo, Model model) {
        if (productNo != null) {
            try {
                ProductEntity product = productService.getProductByNo(productNo);
                model.addAttribute("product", product);
                if (!product.getImages().isEmpty()) {
                    model.addAttribute("mainImage", product.getImages().get(0));
                }
                log.info("Product: {}", product);
            } catch (EntityNotFoundException e) {
                model.addAttribute("errorMessage", "상품을 찾을 수 없습니다.");
                log.error("Product not found", e);
            } catch (Exception e) {
                model.addAttribute("errorMessage", "상품 정보를 불러오는 중 오류가 발생했습니다.");
                log.error("Error fetching product", e);
            }
        } else {
            List<ProductEntity> products = productService.getAllProducts();
            model.addAttribute("products", products);
        }
        return "views/product_detail/product_detail";
    }
}
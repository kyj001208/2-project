package com.green.petfirst.controller.product_detail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Product_detailController {
    @GetMapping("/petfir/product-detail")
    public String getProductDetail(Model model) {
        // 필요한 경우 모델에 데이터를 추가
        return "views/product_detail/product_detail";
    }
}

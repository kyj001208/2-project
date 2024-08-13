package com.green.petfirst.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.petfirst.domain.dto.product.Product_addDTO;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.repository.CategoryRepository;
import com.green.petfirst.service.product.Product_addService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Product_addController {

    private final Product_addService productService;
    private final CategoryRepository categoryRepository; // CategoryRepository 추가

    @PostMapping("/admin/product_add")
    public String addProduct(
            @RequestParam("category3") Long category3,
            @RequestParam("productName") String productName,
            @RequestParam("price") Long price,
            @RequestParam("discount") Long discount,
            @RequestParam("discountedPrice") Long discountedPrice,
            @RequestParam("quantity") Long quantity,
            @RequestParam("productDetail") String productDetail)
 {
        
        // DTO 생성
        Product_addDTO productAddDTO = Product_addDTO.builder()
                .productName(productName)
                .price(price)
                .productDetail(productDetail)
                .quantity(quantity)
                .discount(discount)
                .discountPrice(discountedPrice)
                .category(getCategoryEntity(category3)) // 수정된 부분
                .build();

        // 상품 저장
        productService.addProduct(productAddDTO);

        return "redirect:/admin/product_add"; // 저장 후 상품 목록으로 리디렉션
    }

    private CategoryEntity getCategoryEntity(Long category3) {
        return categoryRepository.findById(category3)
                .orElseThrow(() -> new RuntimeException("Category not found")); // 카테고리 조회 로직
    }
    
	@PostMapping("/admin/fileupload")
	public String fileupload(@RequestParam("itemFile")MultipartFile itemFile) {
		System.out.println("name:"+itemFile.getName());
		System.out.println("contentType:"+itemFile.getContentType());
		System.out.println("orgName:"+itemFile.getOriginalFilename());
		System.out.println("size:"+itemFile.getSize());
		
		return "redirect:/";
	}

}
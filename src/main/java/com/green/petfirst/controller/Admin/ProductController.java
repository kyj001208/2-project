package com.green.petfirst.controller.Admin;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.petfirst.domain.dto.product.ImageSaveDTO;
import com.green.petfirst.domain.dto.product.ProductAddDTO;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.repository.CategoryRepository;
import com.green.petfirst.service.product.ProductAddService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductAddService productService;
    private final CategoryRepository categoryRepository; // CategoryRepository 추가
    
    @GetMapping("/admin/product")
	public String product() {
		return "views/admin/product";
	}

    @PostMapping("/admin/product")
	public String addProduct(@ModelAttribute ProductAddDTO productAddDTO , ImageSaveDTO imageDTO ){
    	log.info(">>>>"+productAddDTO);
    	log.info(">>>>"+imageDTO);
    	
        // 상품 저장
        productService.addProduct(productAddDTO, imageDTO);
    	//return "views/admin/product";
        return "redirect:/admin/product"; // 저장 후 상품 목록으로 리디렉션
    }

    
    
    @ResponseBody//Map<String, String> 성공시 js fetch의 then으로 전달
	@PostMapping("/admin/fileupload")//js 에서 넘오온다, formData.append("itemFile",input.files[0]);
	public Map<String, String> fileupload(@RequestParam("itemFile")MultipartFile itemFile)  throws IOException {
		
		/*
		 * System.out.println("name:"+itemFile.getName());
		 * System.out.println("contentType:"+itemFile.getContentType());
		 * System.out.println("orgName:"+itemFile.getOriginalFilename());
		 * System.out.println("size:"+itemFile.getSize());
		 */
		
		return productService.s3TempUpload(itemFile);
	}

}
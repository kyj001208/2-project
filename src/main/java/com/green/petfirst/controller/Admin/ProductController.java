package com.green.petfirst.controller.Admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.green.petfirst.domain.dto.product.ImageSaveDTO;
import com.green.petfirst.domain.dto.product.ProductAddDTO;
import com.green.petfirst.domain.dto.product.ProductListDTO;
import com.green.petfirst.domain.dto.product.ProductSearchDTO;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.CategoryRepository;
import com.green.petfirst.service.admin.AdminService;
import com.green.petfirst.service.product.ProductAddService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductAddService productService;
   
    
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
    
    
    // 상품 목록 페이지로 이동하면서 페이징된 데이터를 전달
    @GetMapping("/admin/productList")
    public String productList(@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size,
                              Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductListDTO> productPage = productService.getProductList(pageable);
        
        int totalPages = productPage.getTotalPages();
        int currentPage = productPage.getNumber();
        int pageBlockSize = 5; // 한 번에 표시할 페이지 번호 수

        int startPage = (currentPage / pageBlockSize) * pageBlockSize;
        int endPage = Math.min(startPage + pageBlockSize, totalPages);
        
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        return "views/admin/productList";
    }
    
	
    @DeleteMapping("admin/{productNo}")
    public String deleteProduct(@PathVariable("productNo") long productNo) {
        productService.deleteImagesByProductNo(productNo);
        productService.deleteProduct(productNo);
        return "redirect:/admin/productList";
    }
    
    @GetMapping("/public/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<ProductSearchDTO> dto = productService.searchProducts(keyword);
        model.addAttribute("list", dto);
        model.addAttribute("keyword", keyword);  // 키워드를 모델에 추가
        return "views/product/list-search"; // 검색 결과를 보여줄 뷰
    }
}
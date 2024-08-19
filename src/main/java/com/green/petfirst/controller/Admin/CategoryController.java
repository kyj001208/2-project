package com.green.petfirst.controller.Admin;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.green.petfirst.domain.dto.category.CategoryDTO;
import com.green.petfirst.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseBody
    @GetMapping("/admin/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(
            @RequestParam("depth") Long depth,
            @RequestParam(value = "parentNo", required = false) Long parentNo) {
        List<CategoryDTO> categories = categoryService.getCategories(depth, parentNo);
        return ResponseEntity.ok(categories);
    }
    
    //모든카테고리
    @GetMapping("/public/categories")
    public String listCategories(Model model) {
    	System.out.println(">>1");
    	
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("list", categories);
        
        return "views/category/list-data";
    }
    
    @GetMapping("/public/categories/{categoryNo}")
    public String productListpage(@PathVariable("categoryNo") Long categoryNo, Model model) {
    	
    	model.addAttribute("categoryNo", categoryNo);
    	
        return "views/product/list";
    }
    
   
    @GetMapping("/public/categories/{categoryNo}/products")
    public String productList(@PathVariable("categoryNo") Long categoryNo,Model model) {
    	categoryService.categoryProductListProcess(categoryNo, model);
    	String categoryName = categoryService.getCategoryName(categoryNo);
    	model.addAttribute("categoryName", categoryName);
        return "views/product/list-data";
    }
    
   
    @ResponseBody
    @GetMapping("/public/categories/parent")
    public List<CategoryDTO> getParentCategories() {
    	//System.out.println(">>>카테고리 비동기 요청");
        return categoryService.getParentCategories();
    }
    
    @ResponseBody
    @GetMapping("/public/categories/{parentCategoryNo}/children")
    public List<CategoryDTO> getChildCategories(@PathVariable("parentCategoryNo") Long parentCategoryNo) {
        return categoryService.getChildCategories(parentCategoryNo);
    }
    
    
    
    
}
package com.green.petfirst.controller.Admin;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.petfirst.domain.dto.category.CategoryDTO;
import com.green.petfirst.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(
            @RequestParam("depth") Long depth,
            @RequestParam(value = "parentNo", required = false) Long parentNo) {
        List<CategoryDTO> categories = categoryService.getCategories(depth, parentNo);
        return ResponseEntity.ok(categories);
    }
}
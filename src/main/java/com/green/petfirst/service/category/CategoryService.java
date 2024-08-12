package com.green.petfirst.service.category;

import java.util.Collection;
import java.util.List;

import com.green.petfirst.domain.dto.login.CategoryDTO;
import com.green.petfirst.domain.entity.CategoryEntity;


public interface CategoryService {
    
    // 모든 카테고리 조회
    List<CategoryDTO> getAllCategories();

    // 대분류 카테고리 조회
    List<CategoryDTO> getMajorCategories();

    // 중분류 카테고리 조회
    List<CategoryDTO> getMediumCategories();

    // 소분류 카테고리 조회
    List<CategoryDTO> getMinorCategories();
}
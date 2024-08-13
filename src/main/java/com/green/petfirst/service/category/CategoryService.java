package com.green.petfirst.service.category;

import java.util.List;

import com.green.petfirst.domain.dto.category.CategoryDTO;


public interface CategoryService {
    List<CategoryDTO> getCategories(Long depth, Long parentNo);
    List<CategoryDTO> getCategoriesByDepth(Long depth);
}
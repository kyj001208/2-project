package com.green.petfirst.service.category;

import java.util.List;

import com.green.petfirst.domain.dto.login.CategoryDTO;


public interface CategoryService {
    List<CategoryDTO> getAllCategoriesOrdered();
}

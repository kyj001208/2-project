package com.green.petfirst.service.category.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.petfirst.domain.dto.login.CategoryDTO;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.repository.CategoryRepository;
import com.green.petfirst.service.category.CategoryService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryServiceProcess implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategoriesOrdered() {
        List<CategoryEntity> categories = categoryRepository.findAllOrderByParentIdAscCategoryIdAsc();
        return categories.stream()
                         .map(CategoryDTO::of)
                         .collect(Collectors.toList());
    }
}
package com.green.petfirst.service.category.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CategoryDTO> getAllCategories() {
        return convertToDTO(categoryRepository.findAll());
    }

    @Override
    public List<CategoryDTO> getMajorCategories() {
        return convertToDTO(categoryRepository.findByDepth(1)); // 대분류 depth = 1
    }

    @Override
    public List<CategoryDTO> getMediumCategories() {
        return convertToDTO(categoryRepository.findByDepth(2)); // 중분류 depth = 2
    }

    @Override
    public List<CategoryDTO> getMinorCategories() {
        return convertToDTO(categoryRepository.findByDepth(3)); // 소분류 depth = 3
    }

    private List<CategoryDTO> convertToDTO(List<CategoryEntity> categories) {
        return categories.stream()
            .map(category -> new CategoryDTO(category.getCategoryNo(), category.getCategoryName(), category.getDepth(), null))
            .collect(Collectors.toList());
    }
}
package com.green.petfirst.service.category.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.petfirst.domain.dto.category.CategoryDTO;
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
    public List<CategoryDTO> getCategories(Long depth, Long parentNo) {
        List<CategoryEntity> categories;
        if (parentNo == null) {
            categories = categoryRepository.findByDepth(depth);
        } else {
            categories = categoryRepository.findByDepthAndParent_CategoryNo(depth, parentNo);
        }
        return categories.stream()
                         .map(this::convertToDTO)
                         .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategoriesByDepth(Long depth) {
        List<CategoryEntity> categories = categoryRepository.findByDepth(depth);
        return categories.stream()
                         .map(this::convertToDTO)
                         .collect(Collectors.toList());
    }
    
    
    
    private CategoryDTO convertToDTO(CategoryEntity category) {
        return new CategoryDTO(
            category.getCategoryNo(),
            category.getCategoryName(),
            category.getDepth(),
            category.getParent() != null ? category.getParent().getCategoryNo() : null
        );
    }
}
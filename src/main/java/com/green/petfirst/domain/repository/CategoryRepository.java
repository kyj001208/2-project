package com.green.petfirst.domain.repository;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.green.petfirst.domain.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
    // 특정 깊이에 해당하는 카테고리 조회
    List<CategoryEntity> findByDepth(long depth);
}
package com.green.petfirst.domain.repository;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.green.petfirst.domain.dto.category.CategoryDTO;
import com.green.petfirst.domain.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
	@Cacheable("categories")
    List<CategoryEntity> findByDepthAndParent_CategoryNo(long depth, Long parentNo);
	@Cacheable("categories")
    List<CategoryEntity> findByDepth(long depth);
}
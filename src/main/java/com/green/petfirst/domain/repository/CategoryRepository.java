package com.green.petfirst.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.green.petfirst.domain.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	
    @Query("SELECT c FROM CategoryEntity c LEFT JOIN c.parent p ORDER BY COALESCE(p.categoryNo, 0) ASC, c.categoryNo ASC")
    List<CategoryEntity> findAllOrderByParentIdAscCategoryIdAsc();
}
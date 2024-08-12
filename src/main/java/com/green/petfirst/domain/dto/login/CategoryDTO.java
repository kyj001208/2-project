package com.green.petfirst.domain.dto.login;

import java.util.List;
import java.util.stream.Collectors;

import com.green.petfirst.domain.entity.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Long depth;
    private List<CategoryDTO> children;

    public static CategoryDTO of(CategoryEntity category) {
        return new CategoryDTO(
                category.getCategoryNo(),
                category.getCategoryName(),
                category.getDepth(),
                category.getChildren().stream().map(CategoryDTO::of).collect(Collectors.toList())
        );
    }
}

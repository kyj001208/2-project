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
    private long id;
    private String name;
    private long depth;
    private List<CategoryDTO> children;

}

package com.green.petfirst.domain.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private long categoryNo;      // 카테고리 번호
    private String categoryName;  // 카테고리 이름
    private long depth;           // 카테고리 깊이
    private Long parentNo;        // 부모 카테고리 번호 (null 가능)
}
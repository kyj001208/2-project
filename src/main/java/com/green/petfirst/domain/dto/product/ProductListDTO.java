package com.green.petfirst.domain.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {
	
    private long productNo; //상품번호
    
    private String productName;  // 상품명
    
    private long price;  // 상품가격
        
    private long discount;  // 즉시할인
    
    private long discountPrice;  // 즉시할인가

    private String imgUrl;//대표 이미지
}
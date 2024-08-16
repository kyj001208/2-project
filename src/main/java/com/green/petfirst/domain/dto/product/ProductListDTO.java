package com.green.petfirst.domain.dto.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductListDTO {
	
    private long productNo; //상품번호
    
    private String productName;  // 상품명
    
    private long price;  // 상품가격
        
    private long discount;  // 즉시할인
    
    private long discountPrice;  // 즉시할인가

    private String imgUrl;//대표 이미지
}

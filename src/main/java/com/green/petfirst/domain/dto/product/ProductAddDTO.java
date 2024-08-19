package com.green.petfirst.domain.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddDTO {

	private Long productNo; //상품번호
	private Long categoryNo;  // 카테고리 
	private String productName;  // 상품명
	private Long price;  // 상품가격
	private String productDetail;  // 상품설명
	private Long quantity;  // 상품수량
	private Long discount;  // 즉시할인
	private Long discountPrice;  // 즉시할인가	
	
}
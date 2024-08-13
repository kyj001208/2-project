package com.green.petfirst.domain.dto.product;

import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product_addDTO {

	private long productNo; //상품번호
	private CategoryEntity category;  // 카테고리 번호
	private String productName;  // 상품명
	private long price;  // 상품가격
	private String productDetail;  // 상품설명
	private long quantity;  // 상품수량
	private long discount;  // 즉시할인
	private long discountPrice;  // 즉시할인가
	
}

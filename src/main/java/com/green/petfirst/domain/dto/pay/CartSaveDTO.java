package com.green.petfirst.domain.dto.pay;

import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.ProductEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartSaveDTO {
	
	
	//private long productNo;
	
	private String productName;
	
	private String count;
	
	private String price;
	
	
	
	//public CartProductEntity toEntity(ProductEntity product) {
	
	public CartProductEntity toEntity() {
		
		return CartProductEntity.builder()
				//.product(product)
				.productName(productName)
				.count(count)
				.price(price)
				.build();
	}





}

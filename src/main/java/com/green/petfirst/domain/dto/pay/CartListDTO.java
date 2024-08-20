package com.green.petfirst.domain.dto.pay;

import com.green.petfirst.domain.dto.product.ProductListDTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartListDTO {
	
	//private long productNo;
	
	private long cartNo;
	private long marketNo;
	private ProductListDTO product;
	private int count;
	private String productName;
	private long totalprice;
	private String imgUrl;

}

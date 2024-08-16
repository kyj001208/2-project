package com.green.petfirst.domain.dto.pay;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartListDTO {
	
	//private long productNo;
	
	private String productName;
	
	private String count;
	
	private String price;
	
	

	
}

package com.green.petfirst.domain.dto.pay;

import java.util.List;

import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.PayEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySaveDTO {
	

	    private long payNo;
	    private List<String> cartNo;
	    private String recipient; // 수정: String으로 수정
	    private String payAddress;
	    private List<String> productName; // 상품명 리스트
	    private List<String> productPrice; // 가격 리스트
	    
	    
		public PayEntity toEntity(CartProductEntity cartproduct) {
			
			return PayEntity.builder()
					.cartProduct(cartproduct)
					.recipient(recipient)
					.payAddress(payAddress)
					.productName(productName)
					.totalPrice(productPrice)		
					.build();
		}
	

}

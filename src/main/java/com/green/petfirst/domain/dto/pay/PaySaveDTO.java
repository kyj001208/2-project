package com.green.petfirst.domain.dto.pay;

import java.util.List;

import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.PayEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySaveDTO {
    private long payNo;
    private List<Long> cartNo;
    private String recipient;
    private String payAddress;
    private String phone;
    private List<String> productName;
    private List<String> productPrice; // 유지: 문자열 리스트로 변경

    public PayEntity toEntity(CartProductEntity cartproduct) {
        // productPrice는 List<String>으로 설정
        return PayEntity.builder()
            .cartProduct(cartproduct)
            .recipient(recipient)
            .payAddress(payAddress)
            .phoneNum(phone)
            .productName(productName)
            .totalPrice(productPrice) // 문자열 리스트를 그대로 설정
            .build();
    }
}


	  
	    
	    
		
	   
		


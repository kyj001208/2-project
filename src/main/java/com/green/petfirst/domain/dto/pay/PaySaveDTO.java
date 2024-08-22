package com.green.petfirst.domain.dto.pay;

import java.util.List;

import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.DeliverEntity;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.OrderEntity;
import com.green.petfirst.domain.entity.PayEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.entity.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySaveDTO {
    private long payNo;
    private List<Long> cartNo;
    private String recipient;
    private String payAddress;
    private List<Long> quantity;
    private String phone;
    private List<String> productName;
    private List<String> productPrice; // 유지: 문자열 리스트로 변경
    
}


	  
	    
	    
		
	   
		


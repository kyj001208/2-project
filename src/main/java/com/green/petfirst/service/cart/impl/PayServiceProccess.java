package com.green.petfirst.service.cart.impl;

import org.springframework.stereotype.Service;

import com.green.petfirst.domain.dto.pay.PaySaveDTO;
import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.PayEntity;
import com.green.petfirst.domain.repository.CartRepository;
import com.green.petfirst.domain.repository.MarketRepository;
import com.green.petfirst.domain.repository.PayRepository;
import com.green.petfirst.service.cart.PayService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PayServiceProccess implements PayService {
	
	private final PayRepository repository; //결제 레포지토리
	private final CartRepository cartrepository; //장바구니 상품 레포지토리
	
	public void saveProcess(PaySaveDTO dto) {
	    if (dto.getCartNo().isEmpty()) {
	        throw new RuntimeException("No Cart ID provided");
	    }
	    Long cartNo = dto.getCartNo().get(0); // 첫 번째 장바구니 번호를 사용
	    CartProductEntity cartproduct = cartrepository.findById(cartNo)
	        .orElseThrow(() -> new RuntimeException("Cart not found with id " + cartNo));

	    repository.save(dto.toEntity(cartproduct));
	}

}

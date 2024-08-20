package com.green.petfirst.service.cart.impl;

import org.springframework.stereotype.Service;

import com.green.petfirst.domain.dto.pay.PaySaveDTO;
import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.PayEntity;
import com.green.petfirst.domain.repository.CartRepository;
import com.green.petfirst.domain.repository.MarketRepository;
import com.green.petfirst.domain.repository.PayRepository;
import com.green.petfirst.service.cart.PayService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PayServiceProccess implements PayService {

	private final PayRepository repository; // 결제 레포지토리
	private final CartRepository cartrepository; // 장바구니 상품 레포지토리

	@Override
	public void saveProcess(PaySaveDTO dto) {
		for (String cartNoStr : dto.getCartNo()) {
			long cartNo = Long.parseLong(cartNoStr); // 문자열을 long으로 변환
			CartProductEntity cartProduct = cartrepository.findById(cartNo)
					.orElseThrow(() -> new RuntimeException("Cart not found with id " + cartNo));

			repository.save(dto.toEntity(cartProduct));
		}
	}
}

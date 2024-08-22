package com.green.petfirst.service.cart.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.green.petfirst.domain.dto.pay.PaySaveDTO;
import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.DeliverEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.OrderEntity;
import com.green.petfirst.domain.entity.PayEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.entity.Status;
import com.green.petfirst.domain.repository.CartRepository;
import com.green.petfirst.domain.repository.MarketRepository;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.domain.repository.OrderRepository;
import com.green.petfirst.domain.repository.PayRepository;
import com.green.petfirst.service.cart.PayService;

import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PayServiceProccess implements PayService {
	
	private final PayRepository repository; //결제 레포지토리
	private final CartRepository cartrepository; //장바구니 상품 레포지토리
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	
	
	public void saveProcess(PaySaveDTO dto, String email) {
	    
	    //Long cartNo = dto.getCartNo().get(0); // 첫 번째 장바구니 번호를 사용
	    //CartProductEntity cartproduct = cartrepository.findById(cartNo).orElseThrow(() -> new RuntimeException("id 찾을수 없음 " + cartNo));

	    //결제정보 저장
	    
	    MemberEntity memberEntity=memberRepository.findByEmail(email).orElseThrow();
	    List<Long> cartNoes=dto.getCartNo();
	    PayEntity payEntity=PayEntity.builder()
	    		.recipient(dto.getRecipient())
	    		.payAddress(dto.getPayAddress())
	    		.phoneNum(dto.getPhone())
	    		.build();
	    payEntity=repository.save(payEntity);
	    //DeliverEntity deliverEntity=DeliverEntity.builder().devCompany("우체국택배").member(memberEntity).build();	
	    for(int i=0; i<cartNoes.size(); i++) {
	    	CartProductEntity cartproduct = cartrepository.findById(cartNoes.get(i)).orElseThrow();
	    	ProductEntity productEntity=cartproduct.getProduct();
	    	//payEntity.addProducts(productEntity);
	    	
	    	Long quantity=dto.getQuantity().get(i);
	    	Long discountPrice=productEntity.getDiscountPrice();
	    	orderRepository.save(OrderEntity.builder()
	    			//.deliver(deliverEntity)
	    			.product(productEntity)
	    			.member(memberEntity)
	    			.status(Status.PROCESSING)
	    			.quantity(quantity)
	    			.unitPrice(discountPrice)
	    			.total(quantity*discountPrice)
	    			.pay(payEntity)
	    			.build());
	    	
	    	cartrepository.deleteById(cartNoes.get(i));
	    }
	    
	    
	}

}

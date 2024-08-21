package com.green.petfirst.service.cart.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.pay.CartSaveDTO;
import com.green.petfirst.domain.dto.pay.CartUpdateDTO;
import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.PayEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.CartRepository;
import com.green.petfirst.domain.repository.MarketRepository;
import com.green.petfirst.domain.repository.PayRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.security.PetfirUserDetails;
import com.green.petfirst.service.cart.CartSerive;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartSeriveProcess implements CartSerive {

	private final CartRepository repository; // 장바구니 상품
	private final MarketRepository marketRepository;
	private final ProductRepository productRepository; // 상품
	private final PayRepository payrepository;

	// 장바구니 추가 메서드
	@Override
	@Transactional
	public void saveProcess(CartSaveDTO dto, String email) {

		ProductEntity product = productRepository.findById(dto.getProductNo())
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductNo()));
		repository.save(CartProductEntity.builder().product(product)
				.market(marketRepository.findByMember_email(email).orElseThrow()).count(dto.getCount()).totalprice(dto.getTotalPrice()).build());
	}

	// 장바구니 조회 메서드
	@Override
	public void listProcess(PetfirUserDetails user, Model model) {
		// 사용자의 장바구니 조회
		MarketEntity market = marketRepository.findByMember_email(user.getEmail())
				.orElseThrow(() -> new RuntimeException("Market not found for the user"));
		long marketNo = market.getMarketNo();

		// marketNo에 해당하는 장바구니 항목 조회
		List<CartProductEntity> cartItems = repository.findByMarket_MarketNo(marketNo);

		// 조회된 항목들을 모델에 추가
		model.addAttribute("list", cartItems.stream().map(CartProductEntity::tolistDTO).collect(Collectors.toList()));
	}

	// 삭제 처리해주는 메서드
	@Override
	public void deleteProcess(long no) {
		repository.deleteById(no);
	}

	@Override
	@Transactional
	public void updateProcess(long cartProductId, CartUpdateDTO dto) {
	    // 장바구니 상품 조회
	    CartProductEntity cartProduct = repository.findById(cartProductId)
	            .orElseThrow(() -> new RuntimeException("Cart product not found with id: " + cartProductId));

	    // 상품 조회
	    ProductEntity product = cartProduct.getProduct();
	    if (product == null) {
	        throw new RuntimeException("Product not found for cart product with id: " + cartProductId);
	    }

	    // 내 장바구니 번호 조회
	    MarketEntity market = cartProduct.getMarket(); // 현재 장바구니 항목에서 상점 정보 가져오기

	    // 수량 및 가격 업데이트!
	    int newCount = dto.getCount();
	    double newTotalPrice = dto.getTotalprice();
	    
	    if (newCount <= 0) {
	        throw new IllegalArgumentException("Count must be greater than zero.");
	    }
	    if (newTotalPrice < 0) {
	        throw new IllegalArgumentException("Total price must be non-negative.");
	    }
	    //double을 long으로 변환 (소수점 버리기)
	    long newTotalPriceLong = (long) Math.floor(newTotalPrice);
	    // 빌더를 사용하여 새 객체를 생성(세터가 없기에)
	    CartProductEntity updatedCartProduct = CartProductEntity.builder()
	            .cartNo(cartProductId) // 기존 ID 사용
	            .product(product) // 기존 상품 사용
	            .market(market) // 기존 상점 사용
	            .count(newCount) // 새로운 수량
	            .totalprice(newTotalPriceLong) // 새로운 총 가격 (long 타입으로 변환)
	            .build();

	    //만들어진걸 장바구니 상품 저장
	    repository.save(updatedCartProduct);
	}
	
	@Transactional
	@Override
	public void deleteCartProcess(PetfirUserDetails user) {
		/*
	    // 사용자의 장바구니를 조회
	    MarketEntity market = marketRepository.findByMember_email(user.getEmail())
	            .orElseThrow(() -> new RuntimeException("유저를 통해 마켓 no를 찾을 수 없음"));
	    long marketNo = market.getMarketNo();

	    // 장바구니 상품을 조회
	    List<CartProductEntity> cartProducts = repository.findByMarket_MarketNo(marketNo);
	    for (CartProductEntity product : cartProducts) {
	        // 결제 정보와의 연결 해제 처리
	        List<PayEntity> payments = payrepository.findByCartProductNo(product.getCartNo());
	        for (PayEntity payment : payments) {
	            payment.setCartProduct(null); // 결제와의 연결을 해제
	            payrepository.save(payment); // 변경 사항 저장
	        }
	    }

	    // 장바구니 상품 삭제
	    repository.deleteByMarket_MarketNo(marketNo);
	    */
	}

	}

	




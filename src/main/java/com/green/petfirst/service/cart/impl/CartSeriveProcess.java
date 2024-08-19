package com.green.petfirst.service.cart.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.pay.CartSaveDTO;
import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.CartRepository;
import com.green.petfirst.domain.repository.MarketRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.security.PetfirUserDetails;
import com.green.petfirst.service.cart.CartSerive;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartSeriveProcess implements CartSerive {

    private final CartRepository repository;
    private final MarketRepository marketRepository;
    private final ProductRepository productRepository;

    // 장바구니 추가 메서드 
    @Override
    @Transactional
    public void saveProcess(CartSaveDTO dto, String email) {
    	
    	ProductEntity product = productRepository.findById(dto.getProductNo())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductNo()));
        repository.save(CartProductEntity.builder()
        		.product(product)
        		.market(marketRepository.findByMember_email(email).orElseThrow())
        		.count(dto.getCount())
        		.build());
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
           model.addAttribute("list", cartItems.stream()
                   .map(CartProductEntity::tolistDTO)
                   .collect(Collectors.toList()));
       }

    // 삭제 처리해주는 메서드
    @Override
    public void deleteProcess(long no) {
        repository.deleteById(no);
    }
}
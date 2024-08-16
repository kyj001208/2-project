package com.green.petfirst.service.cart.impl;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.pay.CartSaveDTO;
import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.CartRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.service.cart.CartSerive;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartSeriveProcess implements CartSerive{

	private final CartRepository repository;
	
	private final ProductRepository productrepositroy;

	
	//장바구니 추가 메서드 
	@Override
	@Transactional
	public void saveProcess(CartSaveDTO dto) {
		
		//ProductEntity product = productrepositroy.findById(dto.getProductNo())
               // .orElseThrow(() -> new RuntimeException("Product not found"));
		
		repository.save(dto.toEntity());
	}


	//장바구니 조회 메서드
	@Override
	public void listProcess(Model model) {
		
		model.addAttribute("list",repository.findAll().stream()
				.map(CartProductEntity::tolistDTO)
				.collect(Collectors.toList())
				);
		
	}
}

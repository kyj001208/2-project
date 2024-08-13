package com.green.petfirst.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.MemberRepository;
//import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceProcess implements AdminService {

	private final MemberRepository memberRep;
	//private final ProductRepository productRep;

	@Override
	public void ListProcess(Model model) {
        long totalMembers = memberRep.countAllMembers();  // 총 회원 수 계산
        model.addAttribute("totalMembers", totalMembers);  // 모델에 총 회원 수 추가
        
        //List<ProductEntity> products = productRep.findAll();
        //model.addAttribute("products", products);
	}

}

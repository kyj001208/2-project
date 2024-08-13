package com.green.petfirst.service.admin.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.deliver.DeliverDTO;
import com.green.petfirst.domain.entity.DeliverEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.DeliverRepository;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.domain.repository.Product_addRepository;
import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceProcess implements AdminService {

	private final MemberRepository memberRep;
	private final Product_addRepository productRep;
	private final DeliverRepository deliverRep;

	@Override
	public void ListProcess(Model model) {
        long totalMembers = memberRep.countAllMembers();  // 총 회원 수 계산
        model.addAttribute("totalMembers", totalMembers);  // 모델에 총 회원 수 추가
        
        List<ProductEntity> products = productRep.findAll();
        model.addAttribute("products", products);
	}

	@Override
	public void DeliverList(Model model) {
	    List<DeliverEntity> delivers = deliverRep.findAll();
	    List<DeliverDTO> deliverDTO = delivers.stream()
	            .map((DeliverEntity deliver) -> {
	                String address = memberRep.findById(deliver.getMember().getMemNo())
	                        .map(MemberEntity::getAddress)
	                        .orElse("주소 미등록");
	                return DeliverDTO.builder()
	                        .devNo(deliver.getDevNo())
	                        .devCompany(deliver.getDevCompany())
	                        .devTime(deliver.getDevTime())
	                        .devComplete(deliver.getDevComplete())
	                        .memNo(deliver.getMember().getMemNo())
	                        .address(address)
	                        .build();
	            })
	            .collect(Collectors.toList());

	    model.addAttribute("delivers", deliverDTO);
	}



}

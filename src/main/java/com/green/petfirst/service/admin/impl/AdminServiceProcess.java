package com.green.petfirst.service.admin.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.deliver.DeliverDTO;
import com.green.petfirst.domain.entity.DeliverEntity;
import com.green.petfirst.domain.entity.ExchangeRefundEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.DeliverRepository;
import com.green.petfirst.domain.repository.ExchangeRefundRepository;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceProcess implements AdminService {

	private final MemberRepository memberRep;
	private final ProductRepository productRep;
	private final DeliverRepository deliverRep;
	

	@Override
	public void ListProcess(Model model) {
        long totalMembers = memberRep.countAllMembers();  // 총 회원 수 계산
        model.addAttribute("totalMembers", totalMembers);  // 모델에 총 회원 수 추가
        
	}

	@Override
	public void DeliverList(String devNo, String devTime, String devComplete, String devCompany, Model model) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    LocalDate start = (devTime != null && !devTime.isEmpty()) ? LocalDate.parse(devTime, formatter) : null;
	    LocalDate end = (devComplete != null && !devComplete.isEmpty()) ? LocalDate.parse(devComplete, formatter) : null;

	    List<DeliverEntity> delivers = deliverRep.findAll()
	            .stream()
	            .filter(deliver -> (devNo == null || devNo.isEmpty() || String.valueOf(deliver.getDevNo()).contains(devNo))
	                    && (start == null || !deliver.getDevTime().isBefore(start))
	                    && (end == null || !deliver.getDevComplete().isAfter(end))
	                    && (devCompany == null || devCompany.isEmpty() || deliver.getDevCompany().equals(devCompany)))
	            .collect(Collectors.toList());

	    List<DeliverDTO> deliverDTOs = delivers.stream()
	            .map(deliver -> {
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

	    model.addAttribute("delivers", deliverDTOs);
	    model.addAttribute("companies", deliverRep.findDistinctCompanies());
	    model.addAttribute("selectedCompany", devCompany);
	    model.addAttribute("devNo", devNo);
	    model.addAttribute("devTime", devTime);
	    model.addAttribute("devComplete", devComplete);
	}

}

package com.green.petfirst.service.excRef.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.entity.ExchangeRefundEntity;
import com.green.petfirst.domain.entity.RequestStatus;
import com.green.petfirst.domain.repository.ExchangeRefundRepository;
import com.green.petfirst.service.excRef.ExchangeRefundService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExchangeRefundServiceProcess implements ExchangeRefundService {
	
	private final ExchangeRefundRepository exreRep;
	
	
	@Override
	public void exreListProcess(Model model) {
		List<ExchangeRefundEntity> exRe = exreRep.findAll();
        model.addAttribute("exRef", exRe);
	}

	@Override
	public void updateStatus(Long requestNo, RequestStatus requestStatus, String requestType) {
	    // 요청 번호로 엔티티 찾기
	    ExchangeRefundEntity entity = exreRep.findById(requestNo)
	            .orElseThrow(() -> new RuntimeException("Request not found"));

	    // 요청 상태 업데이트
	    entity.setStatus(requestStatus);

	    // 상태 변경된 엔티티 저장
	    exreRep.save(entity);
	}
	
}

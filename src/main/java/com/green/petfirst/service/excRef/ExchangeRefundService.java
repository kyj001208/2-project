package com.green.petfirst.service.excRef;

import org.springframework.ui.Model;

import com.green.petfirst.domain.entity.RequestStatus;

public interface ExchangeRefundService {
	
	void exreListProcess(Model model);

	void updateStatus(Long requestNo, RequestStatus requestStatus, String requestType);
}

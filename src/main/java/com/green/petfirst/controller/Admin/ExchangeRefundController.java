package com.green.petfirst.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.petfirst.domain.entity.RequestStatus;
import com.green.petfirst.service.excRef.ExchangeRefundService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExchangeRefundController {
	
	private final ExchangeRefundService service;
	
	@GetMapping("/admin/exchangeRefund")
	public String exreList(Model model) {
		service.exreListProcess(model);
		return "views/admin/excRef";
	}
	
	@PostMapping("/admin/exchangeRefund/{requestNo}")
    @ResponseBody
    public void updateStatus(
    	    @PathVariable("requestNo") Long requestNo,
    	    @RequestParam("requestStatus") RequestStatus requestStatus,
    	    @RequestParam("requestType") String requestType
    	) {
        service.updateStatus(requestNo, requestStatus, requestType);
    }
	
}

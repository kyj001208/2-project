package com.green.petfirst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService service;
	
	@GetMapping("/admin/petfir")
	public String list(Model model) {
		service.ListProcess(model);
		System.out.println(model);
		return "views/admin/petfir";
	}
	
	@GetMapping("/admin/product_add")
	public String product() {
		return "views/admin/product";
	}
	
	@GetMapping("/admin/order")
	public String order() {
		return "views/admin/order";
	}
	
	@GetMapping("/admin/deliver")
    public String deliver(Model model) {
        service.DeliverList(model);  // 배송 데이터 가져오기
        System.out.println(model);
        return "views/admin/deliver";
    }
	
	@GetMapping("/admin/exchangeRefund")
	public String exchange() {
		return "views/admin/excRef";
	}
	
}

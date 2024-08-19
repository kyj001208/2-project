package com.green.petfirst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService service;
	
	@GetMapping("/admin/petfir")
	public String List(Model model) {
		service.ListProcess(model);
		return "views/admin/petfir";
	}
	
	@GetMapping("/admin/deliver")
	public String deliver(
	        @RequestParam(value = "devNo", required = false) String devNo,
	        @RequestParam(value = "devTime", required = false) String devTime,
	        @RequestParam(value = "devComplete", required = false) String devComplete,
	        @RequestParam(value = "devCompany", required = false) String devCompany,
	        Model model) {

	    service.DeliverList(devNo, devTime, devComplete, devCompany, model);
	    return "views/admin/deliver"; // AJAX를 위한 부분적 뷰 리턴
	}

	
}

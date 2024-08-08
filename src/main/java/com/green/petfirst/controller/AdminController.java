package com.green.petfirst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {
	
	
	@GetMapping("/admin/petfir")
	public String list() {
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
	public String deliver() {
		return "views/admin/deliver";
	}
	
}

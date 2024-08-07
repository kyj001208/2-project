package com.green.petfirst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	
}

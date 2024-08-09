package com.green.petfirst.controller.categorypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategorypageController {
	
	@GetMapping("/public/shop/dogs")
	public String getMypage() {
		return "views/categorypage/dogs_shop.html";
	}


}
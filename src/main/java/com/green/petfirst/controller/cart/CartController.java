package com.green.petfirst.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

	@GetMapping("/public/cart")
	public String cart() {
		
		return "/views/cart/cart";
	}
	
}

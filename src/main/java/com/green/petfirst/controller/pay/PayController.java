package com.green.petfirst.controller.pay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {
	
	@GetMapping("/petfir/pay")
	public String login() {
		return "/views/cart/pay";
	}
	
	
	@GetMapping("/petfir/pay/detail")
	public String paydetail() {
		return "/views/cart/paydetail";
	}

}

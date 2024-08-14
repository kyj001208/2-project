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
	
	
	
	@GetMapping("/petfir/pay/detail/success")
	public String paysuccess() {
		return "/views/cart/success";
	}
	
	
	@GetMapping("/petfir/pay/detail/fail")
	public String payFail() {
		return "/views/cart/fail";
	}
	
	

	
	

}

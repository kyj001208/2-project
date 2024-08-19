package com.green.petfirst.controller.pay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.petfirst.service.cart.CartSerive;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class PayController {
	
	
	private final CartSerive cartservice;
	
	/*
	 * @GetMapping("/petfir/pay") public String login() { return "/views/cart/pay";
	 * }
	 */
	
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
	
	
	@GetMapping("/petfir/pay")
	public String paylist(Model model) {
		
		cartservice.listProcess(model);
		return "/views/cart/pay";
	}
	
	

	
	

}

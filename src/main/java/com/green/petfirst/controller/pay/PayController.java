package com.green.petfirst.controller.pay;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.petfirst.security.PetfirUserDetails;
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
	
	/*
	 * @GetMapping("/petfir/pay/detail") public String paydetail() { return
	 * "/views/cart/paydetail"; }
	 */
	
	
	
	@GetMapping("/petfir/pay/detail/success")
	public String paysuccess() {
		return "/views/cart/success";
	}
	
	
	@GetMapping("/petfir/pay/detail/fail")
	public String payFail() {
		return "/views/cart/fail";
	}
	
	
	@GetMapping("/petfir/pay/detail")
	public String paylist( Model model, @AuthenticationPrincipal PetfirUserDetails user) {
		
		cartservice.listProcess(user,model);
		return "/views/cart/paydetail";
	}
	
	//단건결제
	@GetMapping("/petfir/pay/onepaydetail")
	public String onePay() {
		return "/views/cart/onepaydetail";
	}
	

	
	

}

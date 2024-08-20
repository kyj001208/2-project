package com.green.petfirst.controller.pay;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.petfirst.domain.dto.pay.PaySaveDTO;
import com.green.petfirst.security.PetfirUserDetails;
import com.green.petfirst.service.cart.CartSerive;
import com.green.petfirst.service.cart.PayService;
import com.green.petfirst.service.category.CategoryService;
import com.green.petfirst.service.product.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@Controller
public class PayController {
	
	
	private final CartSerive cartservice;
	private final PayService payservice;
	private final ProductService productService;
	
	//토스용
	@GetMapping("/petfir/pay/detail/success")
	public String paysuccess() {
		return "/views/cart/success";
	}
	
	//토스용
	@GetMapping("/petfir/pay/detail/fail")
	public String payFail() {
		return "/views/cart/fail";
	}
	
	
	@GetMapping("/petfir/pay")
	public String paylist( Model model, @AuthenticationPrincipal PetfirUserDetails user) {
		
		cartservice.listProcess(user,model);
		return "/views/cart/pay";
	}
	
	@GetMapping("/petfir/pay/detail")
	public String paydetalget( ) {
		
		return "/views/cart/paydetail";
	}
	
	
	//단건결제
	@GetMapping("/petfir/pay/onepaydetail")
	public String onePay() {
		
		
		return "/views/cart/onepaydetail";
	}
	
	
	@ResponseBody
	@PostMapping("/petfir/paySaveDoc")
	public String paySave(@RequestBody PaySaveDTO dto) {
		
		payservice.saveProcess(dto);
		
		return "";
	}

	
	

}

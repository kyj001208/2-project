package com.green.petfirst.controller.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.petfirst.domain.dto.pay.CartSaveDTO;
import com.green.petfirst.domain.dto.pay.CartUpdateDTO;
import com.green.petfirst.security.PetfirUserDetails;
import com.green.petfirst.service.cart.CartSerive;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CartController {

	private final CartSerive service;

	@ResponseBody
	@PostMapping("/petfir/saveDoc")
	public ResponseEntity<?> postMethodName(@RequestBody CartSaveDTO dto,
			@AuthenticationPrincipal PetfirUserDetails user) {
		// 서비스에서 데이터 처리
		System.out.println(">>>"+dto);
		service.saveProcess(dto, user.getEmail());

		// JSON 형식으로 응답 반환
		return ResponseEntity.ok().body("{\"success\": true, \"message\": \"저장되었습니다.\"}");

	}

	// save한 데이터 뿌려주기
	@GetMapping("/petfir/cart")
	public String cart( Model model, @AuthenticationPrincipal PetfirUserDetails user) {

		service.listProcess(user, model);
		return "/views/cart/cart";
	}
	
	
	@ResponseBody
	@PutMapping("/petfir/cart/{no}")
	public void updateService(@PathVariable("no")long no, @RequestBody CartUpdateDTO dto) {
		
		service.updateProcess(no, dto);
		
	}
	
	
	
	
	//장바구니에서 개별 삭제하는 매핑
	@DeleteMapping("/petfir/cart/{no}")
	public String delete(@PathVariable("no") long no) {

		service.deleteProcess(no);
		// 삭제 후 장바구니 페이지로 리다이렉트
		return "redirect:/petfir/cart";
	}
	
	
	
	
	
	
	@DeleteMapping("/petfir/clear")
	public String deletCart(@AuthenticationPrincipal PetfirUserDetails user) {
		
		service.deleteCartProcess(user);
		
		return "";
		
		
	}
	
	

}

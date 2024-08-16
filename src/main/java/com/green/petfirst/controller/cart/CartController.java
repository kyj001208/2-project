package com.green.petfirst.controller.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.petfirst.domain.dto.pay.CartSaveDTO;
import com.green.petfirst.service.cart.CartSerive;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CartController {

	private final CartSerive service;

	@ResponseBody
	@PostMapping("/petfir/saveDoc")
	public ResponseEntity<?> postMethodName(@RequestBody CartSaveDTO dto) {
		// 서비스에서 데이터 처리

		service.saveProcess(dto);

		// JSON 형식으로 응답 반환
		return ResponseEntity.ok().body("{\"success\": true, \"message\": \"저장되었습니다.\"}");

	}

	//save한 데이터 뿌려주기 
	@GetMapping("/petfir/cart")
	public String cart(Model model) {

		service.listProcess(model);
		return "/views/cart/cart";
	}

}

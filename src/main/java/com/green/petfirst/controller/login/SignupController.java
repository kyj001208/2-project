package com.green.petfirst.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.green.petfirst.domain.dto.login.SignSaveDTO;
import com.green.petfirst.service.login.SignupService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class SignupController {
	
	private final SignupService service;
	
	//회원가입페이지 이동
	@GetMapping("/public/signup")
	public String signup() {
		return "/views/login/signup";
	}
	
	
	//회원가입페이지 내용 저장 
	@PostMapping("/public/signup")
	public String signup(SignSaveDTO dto) {
		
		service.save(dto);
		
		return "redirect:login?signup";
	}
	

}

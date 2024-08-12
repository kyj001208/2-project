package com.green.petfirst.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.service.login.MemberService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService service;
	
	//회원가입페이지 이동
	@GetMapping("/public/signup")
	public String signup() {
		return "/views/login/signup";
	}
	
	
	//회원가입페이지 내용 저장 
	@PostMapping("/public/signup")
	public String signup(MemberDTO dto) {
		
		service.save(dto);
		
		return "redirect:login?signup";
	}
	

}

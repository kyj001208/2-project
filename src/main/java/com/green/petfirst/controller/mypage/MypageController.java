package com.green.petfirst.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.service.login.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {
	
	private final MemberService service;
	
	@GetMapping("/petfir/mypage/orders")
	public String getMypage() {
		return "views/mypage/orders.html";
	}
	
	@GetMapping("/petfir/mypage/return")
	public String getMyReturn() {
		return "views/mypage/return.html";
	}
	
	/*
	 * @GetMapping("/petfir/mypage/myinfo") 
	 * public String getMyinfo() { 
	 * return "views/mypage/myinfo.html"; }
	 */

    @GetMapping("/petfir/mypage/myinfo/{id}")
    public String getMyInfo(@PathVariable Long id, Model model) {
    	MemberDTO memberDTO = service.finById(id);
        model.addAttribute("member", memberDTO);
        return "views/mypage/myinfo.html";
    }

}
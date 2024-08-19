package com.green.petfirst.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
	
	@GetMapping("/petfir/mypage/orders")
	public String getMypage() {
		return "views/mypage/orders.html";
	}
	
	@GetMapping("/petfir/mypage/return")
	public String getMyReturn() {
		return "views/mypage/return.html";
	}
	
	@GetMapping("/petfir/mypage/myinfo")
	public String getMyinfo() {
		return "views/mypage/myinfo.html";
	}


}
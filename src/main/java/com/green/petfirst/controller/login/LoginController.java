package com.green.petfirst.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	
	@GetMapping("/public/login")
	public String signin(HttpServletRequest request) {
		String referer=request.getHeader("Referer");
		System.out.println("referer"+referer);
		//302리다이렉트 페이지
		if(referer != null && !referer.contains("/public/signup") && !referer.contains("/public/login")) {
		request.getSession().setAttribute("prevPage", referer);
		}
		return "/views/login/login";
	}

	
	

}
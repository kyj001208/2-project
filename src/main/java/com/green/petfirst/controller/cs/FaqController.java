package com.green.petfirst.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {

	@GetMapping("/admin/customer/faq")
	public String list() {
		return "";
	}
}

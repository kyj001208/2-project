package com.green.petfirst.naver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.petfirst.naver.dto.OrgUnitSaveDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@Controller
public class NaverAauth2Controller {

	private final NaverCpService service;
	
	//네이버연동 & DB에 저장된 값 불러오기
	@GetMapping("/admin/oauth2/code")
	public String redirctUri(@RequestParam("code") String code,@RequestParam("state") String state, Model model) throws Exception {
		System.out.println("code: " +code);
		if(state.equals("orgunit.read")) {
			service.orgUnitRead(code,model);
			//service.listProcess(model);
		}else if(state.equals("orgunit")){
			
		}
		
		return "views/cart/admin-naver";
	}
	

	
	
}

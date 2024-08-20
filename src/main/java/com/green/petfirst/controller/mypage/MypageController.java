package com.green.petfirst.controller.mypage;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.domain.dto.login.MemberUpdateDTO;
import com.green.petfirst.security.PetfirUserDetails;
import com.green.petfirst.service.login.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	//마이정보 조회
	@GetMapping("/petfir/mypage/myinfo")
	public String getMyInfo(Model model, @AuthenticationPrincipal PetfirUserDetails user) {
	    // 로그인한 사용자의 이메일을 가져옵니다.
	    String email = user.getEmail();

	    // 사용자 정보를 조회하는 서비스 메서드를 호출합니다.
	    service.findByEmail(email, model);

	    // 마이정보 페이지로 이동합니다.
	    return "views/mypage/myinfo";
	}
	//마이정보 수정
	@ResponseBody
    @PutMapping("/petfir/mypage/myinfo")
    public void updateMember(MemberUpdateDTO dto, @AuthenticationPrincipal PetfirUserDetails user) {
        // 로그인한 사용자의 이메일을 가져옵니다.
        String email = user.getEmail();
        System.out.println(">>>email:"+email);

        // DTO에서 이메일을 확인하여 현재 로그인한 사용자와 일치하는지 검증할 수 있습니다.
       

        // 사용자 정보를 업데이트합니다.
        service.updateMember(email,dto);
    }

}
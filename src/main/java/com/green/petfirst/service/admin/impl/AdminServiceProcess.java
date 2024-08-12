package com.green.petfirst.service.admin.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceProcess implements AdminService {

	private final MemberRepository repository;

	@Override
	public void ListProcess(Model model) {
		// 현재 로그인한 사용자 이메일 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email;

		email = ((UserDetails) principal).getUsername(); // 이메일 주소

		// 이메일 주소로 MemberEntity 찾기
		MemberEntity member = repository.findByEmail(email);

		model.addAttribute("dto", member);
	}

}

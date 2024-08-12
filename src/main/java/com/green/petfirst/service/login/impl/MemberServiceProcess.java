package com.green.petfirst.service.login.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.service.login.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {

	private final MemberRepository repository;
	private final PasswordEncoder pass;
	
	@Override
	public void save(MemberDTO dto) {
		
		repository.save(dto.toEntity(pass));
		
	}

}

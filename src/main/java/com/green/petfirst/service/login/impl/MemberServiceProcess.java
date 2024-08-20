
package com.green.petfirst.service.login.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.repository.MarketRepository;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.service.login.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {

	private final MemberRepository repository;
	private final MarketRepository marketRepository;
	private final PasswordEncoder pass;
	
	@Override
	@Transactional // 두작업 모두 성공하거나 실패하기 위해 사용
	public void save(MemberDTO dto) {
		// 일반회원 저장
		MemberEntity member = repository.save(dto.toEntity(pass));
		//일반 회원 장바구니 자동 생성
		MarketEntity market = MarketEntity.builder().member(member).build();
		marketRepository.save(market);
	}

	@Override
	public void saveSocial(MemberEntity entity) {
		repository.save(entity); // 소셜로그인 저장

		// 소셜 로그인 등의 경우에도 장바구니를 생성할 수 있도록 선택적으로 추가 가능
		MarketEntity market = MarketEntity.builder().member(entity).build();
		marketRepository.save(market);
	}

	@Override
	public void findByEmail(String email, Model model) {
		// 이메일로 사용자 정보를 조회합니다.
		MemberEntity member = repository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));
	
		// 사용자 정보를 DTO로 변환합니다.
		MemberDTO dto = member.toDTO();
	
		// 모델에 사용자 정보를 추가합니다.
		model.addAttribute("dto", dto);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		MemberEntity member = repository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("수정할 회원 정보를 찾을 수 없습니다."));
		member.setEmail(dto.getEmail());
		member.setUserId(dto.getUserId());
		member.setPhone(dto.getPhone());
		member.setAddress(dto.getAddress());
		member.setPetName(dto.getPetName());
		member.setPetBreed(dto.getPetBreed());

		repository.save(member);
		
	}

}

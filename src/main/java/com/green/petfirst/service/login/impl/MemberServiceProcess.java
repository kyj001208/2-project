
package com.green.petfirst.service.login.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	 @Transactional //두작업 모두 성공하거나 실패하기 위해 사용
	 public void save(MemberDTO dto) {
	    //일반회원 저장
		 MemberEntity member = repository.save(dto.toEntity(pass));
	    //일반 회원 장바구니 자동 생성
		 MarketEntity market = MarketEntity.builder()
	                .member(member)
	                .build();
	        marketRepository.save(market);
	    }

	 @Override
	 public void saveSocial(MemberEntity entity) {
	    repository.save(entity); //소셜로그인 저장
	        
	    // 소셜 로그인 등의 경우에도 장바구니를 생성할 수 있도록 선택적으로 추가 가능
	    MarketEntity market = MarketEntity.builder()
	            .member(entity)
	            .build();
	        marketRepository.save(market);
	    }

	    @Override
	    public MemberDTO finById(Long id) {
	        // Optional로 감싸진 MemberEntity를 가져와서 존재하는지 확인
	        Optional<MemberEntity> optionalMemberEntity = repository.findById(id);
	        
	        // optional이 존재하면 MemberEntity를 DTO로 변환하여 반환
	        return optionalMemberEntity
	            .map(MemberEntity::toDTO)
	            .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
	    }

}

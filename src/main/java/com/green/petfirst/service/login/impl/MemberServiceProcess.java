
package com.green.petfirst.service.login.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.domain.dto.login.MemberUpdateDTO;
import com.green.petfirst.domain.dto.order.OrderListDTO;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.OrderEntity;
import com.green.petfirst.domain.entity.Status;
import com.green.petfirst.domain.repository.MarketRepository;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.domain.repository.OrderRepository;
import com.green.petfirst.domain.repository.PayRepository;
import com.green.petfirst.service.login.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {

	private final MemberRepository repository;
	private final MarketRepository marketRepository;
	private final OrderRepository orderRepository; 
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
	@Transactional //"조회+ update" 를 한단위로 처리함
	public void updateMember(String email,MemberUpdateDTO dto) {
		repository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("수정할 회원 정보를 찾을 수 없습니다."))
				.updateInfo(dto);
				
				
	}

	@Override
	public void myOrdersProcess(Model model, String email) {
		List<OrderListDTO> list=orderRepository.findByMember_email(email).stream()
		.map(OrderEntity::toOrderListDTO).collect(Collectors.toList());
		model.addAttribute("list", list);
	}

	@Override
	public void mydataProcess(String email, Model model) {
		List<OrderListDTO> list=orderRepository.findByMember_email(email).stream()
		.map(OrderEntity::toOrderListDTO).collect(Collectors.toList());
								
		model.addAttribute("PENDING", list.stream().filter(dto->dto.getStatus()==Status.PENDING).count());
		model.addAttribute("CONFIRMED", list.stream().filter(dto->dto.getStatus()==Status.CONFIRMED).count());
		model.addAttribute("PROCESSING", list.stream().filter(dto->dto.getStatus()==Status.PROCESSING).count());
		model.addAttribute("DELIVERED", list.stream().filter(dto->dto.getStatus()==Status.DELIVERED).count());
		model.addAttribute("EXCHANGE", list.stream().filter(dto->dto.getStatus()==Status.EXCHANGE ).count() );
		model.addAttribute("REFUND", list.stream().filter(dto->dto.getStatus()==Status.REFUND).count());
		
	}

}

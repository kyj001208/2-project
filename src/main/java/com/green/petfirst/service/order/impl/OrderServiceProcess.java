package com.green.petfirst.service.order.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.order.OrderDTO;
import com.green.petfirst.domain.dto.order.OrderUpdateDTO;
import com.green.petfirst.domain.entity.DeliverEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.OrderEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.entity.Status;
import com.green.petfirst.domain.repository.OrderRepository;
import com.green.petfirst.service.order.OrderService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderServiceProcess implements OrderService{
	
	private final OrderRepository orderRep;
	
	@Override
	public void OrderList(String orderNo, String memNo, String orderDate, Model model) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate date = (orderDate != null && !orderDate.isEmpty()) ? LocalDate.parse(orderDate, formatter) : null;

	    List<OrderEntity> orders = orderRep.findAll()
	            .stream()
	            .filter(order -> 
	                    // 필터 조건이 없으면 모든 데이터 반환
	                    (orderNo == null || orderNo.isEmpty() || String.valueOf(order.getOrderNo()).contains(orderNo))
	                    && (memNo == null || memNo.isEmpty() || String.valueOf(order.getMember().getMemNo()).contains(memNo))
	                    && (date == null || order.getOrderDate().isEqual(date))
	            )
	            .collect(Collectors.toList());

	    List<OrderDTO> orderDTOs = orders.stream()
	            .map(order -> OrderDTO.builder()
	                    .orderNo(order.getOrderNo())
	                    .memNo(order.getMember().getMemNo())
	                    .orderDate(order.getOrderDate())  // LocalDateTime 사용
	                    .status(order.getStatus())
	                    .build())
	            .collect(Collectors.toList());

	    model.addAttribute("orders", orderDTOs);
	    model.addAttribute("orderNo", orderNo);
	    model.addAttribute("memNo", memNo);
	    model.addAttribute("orderDate", orderDate);
	}

	@Override
	public void OrderDetailProcess(long orderNo, Model model) {
	    OrderEntity order = orderRep.findById(orderNo)
	            .orElseThrow(() -> new RuntimeException("해당 주문을 찾을 수 없습니다: " + orderNo));

	    // 주문에 연결된 배송, 결제, 상품, 회원 정보를 가져와야 합니다.
	    DeliverEntity deliver = order.getDeliver();
	    ProductEntity product = order.getProduct();
	    MemberEntity member = order.getMember();

	    // 모델에 추가
	    model.addAttribute("order", order);
	    model.addAttribute("deliver", deliver);
	    model.addAttribute("product", product);
	    model.addAttribute("member", member);
	}


	@Override
	public void MemberOrderProcess(long memNo, Model model) {
	    List<OrderEntity> orders = orderRep.findAllByMemberMemNo(memNo);

	    // DTO로 변환하여 사용할 수 있습니다.
	    List<OrderDTO> orderDTOs = orders.stream()
	            .map(order -> OrderDTO.builder()
	                    .orderNo(order.getOrderNo())
	                    .memNo(order.getMember().getMemNo())
	                    .orderDate(order.getOrderDate())
	                    .status(order.getStatus())
	                    .build())
	            .collect(Collectors.toList());

	    model.addAttribute("orders", orderDTOs);
	    model.addAttribute("memNo", memNo);
	}
	
	@Override
	public void OrderUpdateProcess(OrderUpdateDTO dto) {
	    // 주문 번호로 해당 주문을 조회
	    OrderEntity order = orderRep.findById(dto.getOrderNo())
	            .orElseThrow(() -> new RuntimeException("해당 주문을 찾을 수 없습니다: " + dto.getOrderNo()));

	    // 주문 상태 업데이트
	    order.setStatus(dto.getStatus());

	    // 업데이트된 주문을 저장
	    orderRep.save(order);
	}

	
}

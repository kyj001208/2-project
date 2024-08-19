package com.green.petfirst.service.order.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.order.OrderDTO;
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
    public Page<OrderDTO> OrderList(String orderNo, String memNo, String orderDate, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = (orderDate != null && !orderDate.isEmpty()) ? LocalDate.parse(orderDate, formatter) : null;
        
        // 모든 주문 데이터를 필터링한 후 페이징 처리
        List<OrderDTO> filteredOrders = orderRep.findAll().stream()
                .filter(order ->
                        (orderNo == null || orderNo.isEmpty() || String.valueOf(order.getOrderNo()).contains(orderNo))
                        && (memNo == null || memNo.isEmpty() || String.valueOf(order.getMember().getMemNo()).contains(memNo))
                        && (date == null || order.getOrderDate().isEqual(date))
                )
                .map(order -> OrderDTO.builder()
                        .orderNo(order.getOrderNo())
                        .memNo(order.getMember().getMemNo())
                        .orderDate(order.getOrderDate())
                        .status(order.getStatus())
                        .build())
                .collect(Collectors.toList());

        // 필터링된 데이터로 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredOrders.size());
        List<OrderDTO> pageContent = filteredOrders.subList(start, end);

        // 새로운 Page 객체 생성
        return new PageImpl<>(pageContent, pageable, filteredOrders.size());
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
	public void OrderUpdateProcess(Long orderNo, Status status) {
	    // 주문을 조회합니다
	    OrderEntity order = orderRep.findById(orderNo)
	            .orElseThrow(() -> new RuntimeException("해당 주문을 찾을 수 없습니다: " + orderNo));

	    // 상태를 업데이트합니다
	    order.setStatus(status);

	    // 업데이트된 주문을 저장합니다
	    orderRep.save(order);
	}

}

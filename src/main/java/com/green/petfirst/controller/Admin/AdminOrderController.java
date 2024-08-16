package com.green.petfirst.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.petfirst.domain.dto.order.OrderUpdateDTO;
import com.green.petfirst.service.order.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminOrderController {

	private final OrderService service;
	
	@GetMapping("/admin/order")
	public String order(@RequestParam(value = "orderNo", required = false) String orderNo,
	        @RequestParam(value = "memNo", required = false) String memNo,
	        @RequestParam(value = "orderDate", required = false) String orderDate,
	        Model model) {
	    service.OrderList(orderNo, memNo, orderDate, model);
	    return "views/admin/orders/order";
	}
	
	// 주문번호로 주문 상세 정보 가져오기
    @GetMapping("/admin/order/{orderNo}")
    public String OrderDeatil(@PathVariable("orderNo") long orderNo, Model model) {
        service.OrderDetailProcess(orderNo, model);
        return "views/admin/orders/order-detail"; // 주문 상세 정보를 보여줄 템플릿으로 매핑
    }

    // 회원번호로 해당 회원의 모든 주문 내역 가져오기
    @GetMapping("/admin/member/{memNo}")
    public String MemberOrder(@PathVariable("memNo") long memNo, Model model) {
        service.MemberOrderProcess(memNo, model);
        return "views/admin/orders/member-order"; // 회원의 모든 주문 내역을 보여줄 템플릿으로 매핑
    }
	
	/*
	 * @PutMapping("/admin/order") public String OrderUpdate(@ModelAttribute
	 * OrderUpdateDTO dto) { System.out.println(dto);
	 * service.OrderUpdateProcess(dto); return "redirect:/admin/order"; }
	 */
}

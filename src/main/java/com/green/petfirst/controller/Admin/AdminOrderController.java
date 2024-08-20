package com.green.petfirst.controller.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.petfirst.domain.dto.order.OrderDTO;
import com.green.petfirst.domain.entity.Status;
import com.green.petfirst.service.order.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminOrderController {

	private final OrderService service;
	
	@GetMapping("/admin/order")
    public String order(
        @RequestParam(value = "orderNo", required = false) String orderNo,
        @RequestParam(value = "memNo", required = false) String memNo,
        @RequestParam(value = "orderDate", required = false) String orderDate,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "12") int size,
        Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDTO> orderPage = service.OrderList(orderNo, memNo, orderDate, pageable);

        int totalPages = orderPage.getTotalPages();
        int currentPage = orderPage.getNumber();
        int pageBlockSize = 5; // 한 번에 표시할 페이지 번호 수

        int startPage = (currentPage / pageBlockSize) * pageBlockSize;
        int endPage = Math.min(startPage + pageBlockSize, totalPages);

        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
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
	
    @PutMapping("/admin/order/{orderNo}")
    @ResponseBody
    public void updateOrderStatus(
    		@PathVariable("orderNo") Long orderNo,
    	    @RequestParam("status") Status status
    	    ) {
        service.OrderUpdateProcess(orderNo, status);
    }

}

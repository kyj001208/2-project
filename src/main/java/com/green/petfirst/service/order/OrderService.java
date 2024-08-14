package com.green.petfirst.service.order;

import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.order.OrderUpdateDTO;

public interface OrderService {

	void OrderList(String orderNo, String memNo, String orderDate, Model model);

	void OrderDetailProcess(long orderNo, Model model);

	void MemberOrderProcess(long memNo, Model model);

	void OrderUpdateProcess(OrderUpdateDTO dto);

}

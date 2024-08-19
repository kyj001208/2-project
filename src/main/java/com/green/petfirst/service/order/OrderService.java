package com.green.petfirst.service.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.order.OrderDTO;
import com.green.petfirst.domain.dto.order.OrderUpdateDTO;
import com.green.petfirst.domain.entity.Status;

public interface OrderService {

	void OrderDetailProcess(long orderNo, Model model);

	void MemberOrderProcess(long memNo, Model model);

	void OrderUpdateProcess(Long orderNo, Status status);

	Page<OrderDTO> OrderList(String orderNo, String memNo, String orderDate, Pageable pageable);

}

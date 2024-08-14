package com.green.petfirst.domain.dto.order;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.petfirst.domain.entity.Status;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDTO {
	
	private Long orderNo;
	private LocalDate orderDate;
	private Status status;
	private Long total;
	private Long unitPrice;
	private Long devNo;
	private Long memNo;
	private Long payNo;
	private Long productNo;
	
}

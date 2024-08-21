package com.green.petfirst.domain.dto.order;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.petfirst.domain.dto.product.ProductListDTO;
import com.green.petfirst.domain.entity.Status;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderListDTO {
	
	private Long orderNo;
	private ProductListDTO product;
	private long quantity;
	private long unitPrice;
	private long total;
	private LocalDate orderDate;
	
	private Status status;
	
}

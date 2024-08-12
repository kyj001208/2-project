package com.green.petfirst.domain.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class OrderEntity {

	@Id
	private long orderNo; // 교환/환불(pk)
	
	@OneToOne
	@JoinColumn(name = "devNo", nullable = false)
	private DeliverEntity deliver; // 배송번호 (fk)
	
	@OneToOne
	@JoinColumn(name = "payNo", nullable = false)
	//private PayEntity pay; // 주문번호 (fk)
	
	@ManyToOne
	@JoinColumn(name = "productNo", nullable = false)
//	private ProductEntity product; // 주문번호 (fk)
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status; // 요청상태
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp", nullable = false)
	private LocalDate requestDate; // 요청일자
	
	@Column(columnDefinition = "text", nullable = false)
	private String reason; // 사유

	
	
}

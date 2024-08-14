package com.green.petfirst.domain.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity {

	@Id
	private long orderNo; // 교환/환불(pk)
	
	@OneToOne
	@JoinColumn(name = "devNo", nullable = false)
	private DeliverEntity deliver; // 배송번호 (fk)
	
	@OneToOne
	@JoinColumn(name = "payNo", nullable = false)
	private PayEntity pay; // 결제번호 (fk)
	
	@ManyToOne
	@JoinColumn(name = "productNo", nullable = false)
	private ProductEntity product; // 상품번호 (fk)
	
	@ManyToOne
	@JoinColumn(name = "memNo", nullable = false)
	private MemberEntity member; // 회원번호 (fk)
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status; // 요청상태
	
	
	@Column(nullable = false)
	private long quantity;
	
	@Column(nullable = false)
	private long unitPrice;
	
	@Column(nullable = false)
	private long total;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp", nullable = false)
	private LocalDate orderDate; //주문일자

	public void setStatus(Status status) {
		this.status = status;
		
	}
	
	
}

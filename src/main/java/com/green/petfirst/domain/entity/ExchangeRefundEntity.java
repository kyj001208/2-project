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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@Entity
@Table(name = "exchangeRefund")
public class ExchangeRefundEntity {

	@Id
	private long requestNo; // 교환/환불(pk)

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RequestType requestType; // 요청유형

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RequestStatus requestStatus; // 요청상태
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp", nullable = false)
	private LocalDate requestDate; // 요청일자
	
	@Column(columnDefinition = "text", nullable = false)
	private String reason; // 사유

	@ManyToOne
	@JoinColumn(name = "memNo", nullable = false)
	private MemberEntity member; // 회원번호 (fk)
	
	@ManyToOne
	@JoinColumn(name = "orderNo", nullable = false)
	private OrderEntity order; // 주문번호 (fk)

	public void setStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
	
}

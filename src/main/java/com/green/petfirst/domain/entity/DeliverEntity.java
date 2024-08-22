package com.green.petfirst.domain.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name = "deliver")
public class DeliverEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long devNo; //배송코드(pk)
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp", nullable = false)
	private LocalDate devTime; // 배송일자
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp", nullable = false)
	private LocalDate devComplete; // 배송완료일자
	
	@Column(nullable = false)
	private String devCompany; //배송업체
	
	@ManyToOne
	@JoinColumn(name = "memNo", nullable = false)
	private MemberEntity member; // 회원번호 (fk)
}

package com.green.petfirst.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "market")
@Entity
public class MarketEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long marketNo; //장바구니번호
	
	
	
	@OneToOne
	@JoinColumn(name = "memNo", nullable = false)
	private MemberEntity member; //이름
}

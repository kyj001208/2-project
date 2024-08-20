package com.green.petfirst.domain.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.green.petfirst.security.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "pay")
@Entity
public class PayEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long payNo; // 결제테이블 번호

	@OneToOne
	@JoinColumn(name = "cartNo")
	private CartProductEntity cartProduct; // 장바구니 상품 번호
	
	
	@OneToOne
	@JoinColumn(name = "MarketNo")
	private CartProductEntity market; // 장바구니 상품 번호

	// priave MarketEntity market; //장바구니

	private String recipient;// 받는사람

	private String payAddress; // 받는 주소
	
	private String phoneNum;

	@ElementCollection
	@CollectionTable(name = "pay_product_details", joinColumns = @JoinColumn(name = "pay_no"))
	@Column(name = "productName")
	private List<String> productName; // 상품명 리스트

	@ElementCollection
	@CollectionTable(name = "pay_product_details", joinColumns = @JoinColumn(name = "pay_no"))
	@Column(name = "totalPrice")
	private List<String> totalPrice; // 가격 리스트

	private long paymentKey; // 결제테이블 번호

	private long orderId;

}

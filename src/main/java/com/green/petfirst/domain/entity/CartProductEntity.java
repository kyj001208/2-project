package com.green.petfirst.domain.entity;

import java.util.function.Function;

import org.hibernate.annotations.DynamicUpdate;

import com.green.petfirst.domain.dto.pay.CartListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "cartproduct")
@Entity
public class CartProductEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartNo; 
	
	@ManyToOne
	@JoinColumn(name = "productNo")
	private ProductEntity product; 
	
	@ManyToOne
	@JoinColumn(name = "marketNo")
	private MarketEntity market; //장바구니 번호
	
	
	private String productName;  //상품명
	
	
	
	private String count; 
	
	
	
	private String price; 
	
	
	private String totalprice;


	public CartListDTO tolistDTO() {
		
		return CartListDTO.builder()
				.productName(productName)
				.count(count)
				.price(price)
				.build();
	}
	
	
	}

	

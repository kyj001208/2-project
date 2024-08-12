package com.green.petfirst.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

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
public class CartProduct {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartNo; 
	
	@ManyToOne
	@JoinColumn(name = "productNo", nullable = false)
	private ProductEntity product; 
	
	@ManyToOne
	@JoinColumn(name = "marketNo", nullable = false)
	private MarketEntity market; 
	
	@Column(nullable = false)
	private long count; 
	}

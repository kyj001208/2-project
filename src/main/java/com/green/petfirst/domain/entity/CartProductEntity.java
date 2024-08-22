package com.green.petfirst.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.green.petfirst.domain.dto.pay.CartListDTO;
import com.green.petfirst.domain.dto.product.ProductListDTO;

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

@Setter
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
	private MarketEntity market; // 장바구니 번호

	//private String productName; // 상품명

	//@Column(columnDefinition = "blob")
	//private String imgUrl;

	private int count;

	//private String price;

	private long totalprice;

	public CartListDTO tolistDTO() {
	    ProductListDTO productDTO = product.toProductListDTO();
	    return CartListDTO.builder()
	        .cartNo(cartNo)
	        .product(productDTO)
	        .productName(productDTO.getProductName())
	        .totalprice(totalprice)
	        .imgUrl(productDTO.getImgUrl())  // ProductListDTO에서 이미지 URL 가져오기
	        .count(count)
	        .build();
	}



	
}

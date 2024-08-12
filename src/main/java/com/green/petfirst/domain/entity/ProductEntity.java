package com.green.petfirst.domain.entity;

import org.hibernate.annotations.DynamicUpdate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productNo; //상품번호

    @ManyToOne
    @JoinColumn(name = "categoryNo")
    private CategoryEntity category;  // 카테고리 번호
    
    @ManyToOne
    @JoinColumn(name = "memNo")
    private MemberEntity member;  // 회원번호
    
    @Column(nullable = false)
    private String productName;  // 상품명
    
    @Column(nullable = false)
    private long price;  // 상품가격
    
    @Column(columnDefinition = "text", nullable = false)
    private String productDetail;  // 상품설명
    
    @Column(nullable = false)
    private long quantity;  // 상품수량
    
    private long discount;  // 즉시할인
    
    private long discountPrice;  // 즉시할인가


}
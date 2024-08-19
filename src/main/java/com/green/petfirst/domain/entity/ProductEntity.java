package com.green.petfirst.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import com.green.petfirst.domain.dto.product.ProductListDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
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

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ImageEntity> images;//수정한거
    
    
    public ProductListDTO toProductListDTO() {
    	String imgUrl=null;
    	if(images==null||images.isEmpty()) {
    		//대체이미지 적용
    		imgUrl="/img/no-img.jpg";
    	}else {
    		imgUrl=images.get(0).getImgUrl();
    	}
    	
    	return ProductListDTO.builder()
    			.productNo(productNo)
    			.productName(productName)
    			.price(price)
    			.discount(discount)
    			.discountPrice(discountPrice)
    			
    			.imgUrl(imgUrl)
    			.build();
    }
    
    

}
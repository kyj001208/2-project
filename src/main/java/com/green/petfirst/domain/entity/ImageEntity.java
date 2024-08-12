package com.green.petfirst.domain.entity;

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

@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="image")
public class ImageEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imgNo; //이미지 번호
    
    @ManyToOne
    @JoinColumn(name = "productNo")
	private ProductEntity productNo; //상품번호
    
    @Column(columnDefinition = "Blob", nullable = false)
	private String imgUrl; //이미지url
    
    @Column(nullable = false)
	private String bucketKey; //버킷키
	
    @Column(nullable = false)
	private String fileName; //파일이름
	
}

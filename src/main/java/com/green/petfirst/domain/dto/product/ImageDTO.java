package com.green.petfirst.domain.dto.product;

import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

	private long imgNo; //이미지 번호
	private ProductEntity product; //상품번호
	private String imgUrl; //이미지url
	private String bucketKey; //버킷키
	private String fileName; //파일이름
}

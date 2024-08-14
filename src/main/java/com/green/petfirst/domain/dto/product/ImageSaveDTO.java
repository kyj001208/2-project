package com.green.petfirst.domain.dto.product;

import java.util.List;

import com.green.petfirst.domain.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ImageSaveDTO {

	private List<String> tempKey; //버킷키
	private List<String> orgName; //파일이름
}

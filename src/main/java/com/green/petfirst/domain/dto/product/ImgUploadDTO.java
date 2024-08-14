package com.green.petfirst.domain.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.green.petfirst.domain.entity.ImageEntity;

import lombok.Builder;

@Builder
public class ImgUploadDTO {
	private List<String> uploadUrls;
	private List<String> uploadKeys;
	private List<String> orgNames;
	public ImgUploadDTO addOrgNames(List<String> orgNames) {
		this.orgNames=orgNames;
		return this;
	}
	
	public List<ImageEntity> toEntityList() {
		List<ImageEntity> entityList=new ArrayList<>();
		for(int i=0; i<orgNames.size(); i++) {
			entityList.add(ImageEntity.builder()
					.imgUrl(uploadUrls.get(i))
					.bucketKey(uploadKeys.get(i))
					.fileName(orgNames.get(i))
					.build());
		}
		
		return entityList;
	}
}

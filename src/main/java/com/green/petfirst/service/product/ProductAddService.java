package com.green.petfirst.service.product;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.petfirst.domain.dto.product.ImageSaveDTO;
import com.green.petfirst.domain.dto.product.ProductAddDTO;

public interface ProductAddService {
	void addProduct(ProductAddDTO productAddDTO, ImageSaveDTO imageDTO);

	Map<String, String> s3TempUpload(MultipartFile itemFile) throws IOException ;
	

	// 페이징을 지원하는 상품 목록 조회
	Page<ProductAddDTO> getProductList(Pageable pageable);

	
}
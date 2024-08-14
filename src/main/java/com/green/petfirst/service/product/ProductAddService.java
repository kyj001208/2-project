package com.green.petfirst.service.product;


import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.green.petfirst.domain.dto.product.ImageSaveDTO;
import com.green.petfirst.domain.dto.product.ProductAddDTO;

public interface ProductAddService {
	void addProduct(ProductAddDTO productAddDTO, ImageSaveDTO imageDTO);

	Map<String, String> s3TempUpload(MultipartFile itemFile) throws IOException ;
}
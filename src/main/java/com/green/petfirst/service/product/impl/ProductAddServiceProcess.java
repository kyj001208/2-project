package com.green.petfirst.service.product.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.petfirst.domain.dto.product.ImageSaveDTO;
import com.green.petfirst.domain.dto.product.ImgUploadDTO;
import com.green.petfirst.domain.dto.product.ProductAddDTO;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.entity.ImageEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.CategoryRepository;
import com.green.petfirst.domain.repository.ImagesRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.service.product.ProductAddService;
import com.green.petfirst.utils.FileUploadUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductAddServiceProcess implements ProductAddService {

    private final ProductRepository productRepository;
    private final ImagesRepository imagesRepository;
    private final FileUploadUtil fileUploadUtil;//s3에 파일을 업로드하는 util
    private final CategoryRepository cateReop;
    
    @Value("${spring.cloud.aws.s3.upload-src}")
	private String upload;
    
    @Override
    public void addProduct(ProductAddDTO productAddDTO,ImageSaveDTO imageDTO) {
    	ImgUploadDTO imgUploadDTO=fileUploadUtil.s3TempToImages(imageDTO.getTempKey()).addOrgNames(imageDTO.getOrgName());
    	//CategoryEntity category=CategoryEntity.builder().categoryNo(productAddDTO.getCategoryNo()).build();
    	CategoryEntity category=cateReop.findById(productAddDTO.getCategoryNo()).orElseThrow();
        // DTO를 Entity로 변환
        ProductEntity productEntity = ProductEntity.builder()
                .productName(productAddDTO.getProductName())
                .price(productAddDTO.getPrice())
                .productDetail(productAddDTO.getProductDetail())
                .quantity(productAddDTO.getQuantity())
                .discount(productAddDTO.getDiscount())
                .discountPrice(productAddDTO.getDiscountPrice())
                .category(category) // 카테고리 설정
                .build();

        // 상품 엔티티 저장
               
        saveProductAndImages(productEntity, imgUploadDTO.toEntityList());
    }
    
    @Transactional
	private void saveProductAndImages(ProductEntity productEntity, List<ImageEntity> imageEntityList) {
		productEntity= productRepository.save(productEntity);
		for(ImageEntity imageEntity:imageEntityList) {
			imagesRepository.save(imageEntity.product(productEntity));
		}
	}

	@Override
	public Map<String, String> s3TempUpload(MultipartFile itemFile) throws IOException {
		return fileUploadUtil.s3TempUpload(itemFile);
	}
	// 페이징을 지원하는 상품 목록 조회
    @Override
    public Page<ProductAddDTO> getProductList(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::convertToDTO);
    }

    private ProductAddDTO convertToDTO(ProductEntity product) {
        return ProductAddDTO.builder()
            .productNo(product.getProductNo())
            .categoryNo(product.getCategory().getCategoryNo())
            .productName(product.getProductName())
            .price(product.getPrice())
            .productDetail(product.getProductDetail())
            .quantity(product.getQuantity())
            .discount(product.getDiscount())
            .discountPrice(product.getDiscountPrice())
            .build();
    }

    
    
}
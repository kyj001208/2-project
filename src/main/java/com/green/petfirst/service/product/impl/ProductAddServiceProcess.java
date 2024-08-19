package com.green.petfirst.service.product.impl;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.petfirst.domain.dto.product.ImageSaveDTO;
import com.green.petfirst.domain.dto.product.ImgUploadDTO;
import com.green.petfirst.domain.dto.product.ProductAddDTO;
import com.green.petfirst.domain.dto.product.ProductListDTO;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.entity.ImageEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.CategoryRepository;
import com.green.petfirst.domain.repository.ImagesRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.service.product.ProductAddService;
import com.green.petfirst.utils.FileUploadUtil;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

@Service
@RequiredArgsConstructor
public class ProductAddServiceProcess implements ProductAddService {

    private final ProductRepository productRepository;
    private final ImagesRepository imagesRepository;
    private final FileUploadUtil fileUploadUtil;//s3에 파일을 업로드하는 util
    private final CategoryRepository cateReop;
    private final S3Client s3Client;
    
    @Value("${spring.cloud.aws.s3.upload-src}")
	private String upload;
    
    @Override
    public void addProduct(ProductAddDTO productAddDTO, ImageSaveDTO imageDTO) {
        // 카테고리 번호가 null인 경우 예외 처리
        if (productAddDTO.getCategoryNo() == null) {
            throw new IllegalArgumentException("카테고리 번호가 null입니다. 유효한 카테고리 번호를 입력하세요.");
        }

        // S3 이미지 업로드
        ImgUploadDTO imgUploadDTO = fileUploadUtil.s3TempToImages(imageDTO.getTempKey()).addOrgNames(imageDTO.getOrgName());

        // 카테고리 엔티티 조회
        CategoryEntity category = cateReop.findById(productAddDTO.getCategoryNo())
            .orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다. 카테고리 번호: " + productAddDTO.getCategoryNo()));

        // DTO를 Entity로 변환
        ProductEntity productEntity = ProductEntity.builder()
            .productName(productAddDTO.getProductName())
            .price(productAddDTO.getPrice())
            .productDetail(productAddDTO.getProductDetail())
            .quantity(productAddDTO.getQuantity())
            .discount(productAddDTO.getDiscount())
            .discountPrice(productAddDTO.getDiscountPrice())
            .category(category) // 대분류, 중분류, 소분류 설정
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
	public Page<ProductListDTO> getProductList(Pageable pageable) {
	    Page<ProductEntity> productList = productRepository.findAllOrderByProductNoDesc(pageable);
	    
	    // ProductEntity를 ProductListDTO로 매핑하여 리스트로 변환
	    List<ProductListDTO> productDTOList = productList.stream()
	            .map(ProductEntity::toProductListDTO)
	            .collect(Collectors.toList());
	    
	    // 새로운 Page 객체 생성
	    return new PageImpl<>(productDTOList, pageable, productList.getTotalElements());
	}
	// 상품 삭제
	@Override
	public void deleteProduct(long no) {
       
        productRepository.delete(productRepository.findByProductNo(no));	
	}
	 // 이미지 삭제
	@Override
	public void deleteImagesByProductNo(long productNo) {
        // 상품 번호에 해당하는 모든 이미지를 조회
        List<ImageEntity> images = imagesRepository.findByProduct_ProductNo(productNo);
        for (ImageEntity image : images) {
            // S3에서 이미지 파일 삭제
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket("image-bucket-123") // S3 버킷 이름
                .key(image.getBucketKey()) // 이미지의 S3 키
                .build();
            s3Client.deleteObject(deleteObjectRequest);

            // 데이터베이스에서 이미지 정보 삭제
            imagesRepository.delete(image);
        }
    }

    
    
}
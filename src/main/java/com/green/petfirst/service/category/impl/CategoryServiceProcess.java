package com.green.petfirst.service.category.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.green.petfirst.domain.dto.category.CategoryDTO;
import com.green.petfirst.domain.dto.product.ProductListDTO;
import com.green.petfirst.domain.entity.CategoryEntity;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.CategoryRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.service.category.CategoryService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryServiceProcess implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategories(Long depth, Long parentNo) {
        List<CategoryEntity> categories;
        if (parentNo == null) {
            categories = categoryRepository.findByDepth(depth);
        } else {
            categories = categoryRepository.findByDepthAndParent_CategoryNo(depth, parentNo);
        }
        return categories.stream()
                         .map(this::convertToDTO)
                         .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategoriesByDepth(Long depth) {
        List<CategoryEntity> categories = categoryRepository.findByDepth(depth);
        return categories.stream()
                         .map(this::convertToDTO)
                         .collect(Collectors.toList());
    }
    
    
    
    private CategoryDTO convertToDTO(CategoryEntity category) {
        return new CategoryDTO(
            category.getCategoryNo(),
            category.getCategoryName(),
            category.getDepth(),
            category.getParent() != null ? category.getParent().getCategoryNo() : null,
            		null
            		
        );
    }
/**
 * 상품 조회
 */
    
    @Transactional
	@Override
	public void categoryProductListProcess(Long categoryNo, Model model) {
		// 1차-2차-3차 상품은 연결이 3차에만 연결됨
		CategoryEntity category=categoryRepository.findById(categoryNo).orElseThrow();
		//FetchType.LAZY 이기에 session이 유지되어야한다.  //@Transactional 적용함
		List<ProductEntity> list=null;
		if (category.getParent() == null || category.getParent().getParent() == null) {
            // 1차 또는 2차 카테고리인 경우
            List<Long> categoryNoes = getAllSubCategoryIds(category); 
            list=productRepository.findByCategory_categoryNoIn(categoryNoes); //해당 카테고리와 그 하위 카테고리들에 속하는 모든 상품들을 가져와
			
        } else {
            // 3차 카테고리인 경우
        	list=productRepository.findByCategory_categoryNo(categoryNo); //3차인 경우 하위 카테고리가 더 존재하지 않기 때문에, 단순히 해당 카테고리의 categoryNo에 일치하는 상품들만 조회
        }
		
		model.addAttribute("list", list.stream()
				.map(ProductEntity::toProductListDTO)
				.collect(Collectors.toList()));
	}
    
    /** 
     * 사이드 카테고리 메뉴 
     * 주어진 카테고리와 그 하위의 모든 카테고리 번호를 수집하는 메서드
     * @param category 시작 카테고리
     * @return 모든 하위 카테고리(자신 포함)의 번호 리스트
     */
    private List<Long> getAllSubCategoryIds(CategoryEntity category) { 
        // 결과를 저장할 빈 리스트 생성
        List<Long> categoryNoes = new ArrayList<>();
        
        // 재귀 메서드를 호출하여 모든 하위 카테고리 번호 수집
        collectSubCategoryNoes(category, categoryNoes);
        
        // 수집된 모든 카테고리 번호 반환
        return categoryNoes;
    }

    /**
     * 재귀적으로 카테고리와 그 하위 카테고리의 번호를 수집하는 메서드
     * 주의: 잘못 설계하면 무한루프에 빠질 수 있음
     * @param category 현재 처리 중인 카테고리
     * @param ids 카테고리 번호를 저장할 리스트
     */
    private void collectSubCategoryNoes(CategoryEntity category, List<Long> categoryNoes) {
        // 현재 카테고리의 번호를 리스트에 추가
    	categoryNoes.add(category.getCategoryNo());

        // 현재 카테고리의 모든 자식 카테고리에 대해 반복
        for (CategoryEntity child : category.getChildren()) {
            // 각 자식 카테고리에 대해 재귀적으로 이 메서드를 호출
            // 이를 통해 모든 하위 레벨의 카테고리까지 탐색
            collectSubCategoryNoes(child, categoryNoes);
        }
    }

    /**
     * 카테고리를 열면 최상위 카테고리 조회
     * 카테고리 목록을 불러오는 서비스
     * js에서 프론트 처리하도록 설계함.
     */
	@Override
	public List<CategoryDTO> getParentCategories() {
			
		return categoryRepository.findByParentIsNull().stream()
				.map(CategoryEntity::toCategoryDTO)
				.collect(Collectors.toList());
	}

	
	//부모카테고리가 일치하는 카테고리-parentCategoryNo의 하위카테고리
	//카테고리별 상품 필터링
	@Override
	public List<CategoryDTO> getChildCategories(Long parentCategoryNo) {
		
		return categoryRepository.findByParent_categoryNo(parentCategoryNo).stream()
				.map(CategoryEntity::toCategoryDTO)
				.collect(Collectors.toList());
	}

	
	@Transactional
	@Override
	public List<CategoryDTO> getAllCategories() {
		System.out.println("-----");
		
        List<CategoryEntity> rootCategories = categoryRepository.findByParentIsNull();
        List<CategoryDTO> result=rootCategories.stream()
		.map(CategoryEntity::toCategoryDTO)
		.collect(Collectors.toList());
        
        System.out.println("결과"+result.size());
        return result;
    }

	@Override
    public String getCategoryName(Long categoryNo) {
        return categoryRepository.findById(categoryNo)
            .map(CategoryEntity::getCategoryName)
            .orElse("카테고리 없음");  // 기본값
    }

	@Override
	public List<ProductListDTO> getRecommendedProducts() {
	    List<ProductEntity> topDiscountedProducts = productRepository.findTop4ByOrderByDiscountDesc();
	    return topDiscountedProducts.stream()
	        .map(ProductEntity::toProductListDTO) // Entity를 DTO로 변환
	        .collect(Collectors.toList());
	}

	@Override
	public List<ProductListDTO> getNewProduct() {
		List<ProductEntity> productNoDesc = productRepository.findTop4ByOrderByProductNoDesc();
		return productNoDesc.stream()
				.map(ProductEntity::toProductListDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductListDTO> getReasonably() {
		List<ProductEntity> discountprice = productRepository.findTop4ByPriceLessThanEqual(5000L);
		return discountprice.stream()
				.map(ProductEntity::toProductListDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ProductListDTO getToday(long productNo) {
        // 상품 번호로 상품을 조회합니다.

        // ProductEntity의 toProductListDTO 메서드를 사용하여 ProductListDTO로 변환합니다.
        return productRepository.findByProductNo(productNo).map(ProductEntity::toProductListDTO).orElseThrow();
    }







   
}
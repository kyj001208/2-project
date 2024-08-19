package com.green.petfirst.service.category;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.green.petfirst.domain.dto.category.CategoryDTO;
import com.green.petfirst.domain.dto.product.ProductListDTO;


public interface CategoryService {
	
    List<CategoryDTO> getCategories(Long depth, Long parentNo);
    
    List<CategoryDTO> getCategoriesByDepth(Long depth);
    
	void categoryProductListProcess(Long categoryNo, Model model);

	List<CategoryDTO> getParentCategories();

	List<CategoryDTO> getChildCategories(Long parentCategoryNo);

	List<CategoryDTO> getAllCategories();

	String getCategoryName(Long categoryNo);

	List<ProductListDTO> getRecommendedProducts();

	List<ProductListDTO> getNewProduct();

	List<ProductListDTO> getReasonably();


	ProductListDTO getToday(long productNo);


	
	
}
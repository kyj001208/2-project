package com.green.petfirst.controller.Index;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.petfirst.domain.dto.product.ProductListDTO;
import com.green.petfirst.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	private final CategoryService categoryService;
	
	
	@GetMapping("/public/index/recommendedProduct")
	public String getRecommended(Model model) {
	    List<ProductListDTO> products = categoryService.getRecommendedProducts();
	    model.addAttribute("products", products); // 모델에 데이터를 추가합니다.
	    return "views/index/list-data"; 
	}
	
	@GetMapping("/public/index/newProduct")
	public String getNewProduct(Model model) {
	    List<ProductListDTO> products = categoryService.getNewProduct();
	    model.addAttribute("products", products); // 모델에 데이터를 추가합니다.
	    return "views/index/list-data"; 
	}
	
	@GetMapping("/public/index/reasonablyProduct")
	public String getReasonably(Model model) {
	    List<ProductListDTO> products = categoryService.getReasonably();
	    model.addAttribute("products", products); // 모델에 데이터를 추가합니다.
	    return "views/index/list-data"; 
	}
	
	
	@GetMapping("/public/index/today/{productNo}")
	public String getToday(@PathVariable("productNo") long productNo, Model model) {
	    ProductListDTO product = categoryService.getToday(productNo);
	    model.addAttribute("product", product); // 모델에 데이터를 추가합니다.
	    return "views/index/today-data"; // 뷰 이름을 반환합니다.
	}
	
    static class TimerInfo {
        private long hoursLeft;
        private long minutesLeft;
        private long secondsLeft;

        public TimerInfo(long hoursLeft, long minutesLeft, long secondsLeft) {
            this.hoursLeft = hoursLeft;
            this.minutesLeft = minutesLeft;
            this.secondsLeft = secondsLeft;
        }

        public long getHoursLeft() {
            return hoursLeft;
        }

        public long getMinutesLeft() {
            return minutesLeft;
        }

        public long getSecondsLeft() {
            return secondsLeft;
        }
    }
}
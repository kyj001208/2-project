package com.green.petfirst.controller.categorypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategorypageController {
	
	@GetMapping("/public/shop/dogs")
	public String getDogsShoplist() {
		return "views/categorypage/dogs_shop/food.html";
	}
	@GetMapping("/public/shop/dogs/food")
	public String getDogsShopfood() {
		return "views/categorypage/dogs_shop/food.html";
	}
	@GetMapping("/public/shop/dogs/supplies")
	public String getDogsShopSupplies() {
		return "views/categorypage/dogs_shop/supplies.html";
	}
	
	@GetMapping("/public/shop/dogs/toys")
	public String getDogsShopToys() {
		return "views/categorypage/dogs_shop/toys.html";
	}
	
	@GetMapping("/public/shop/dogs/supplements")
	public String getDogsShopSupplements() {
		return "views/categorypage/dogs_shop/supplements.html";
	}
	
	@GetMapping("/public/shop/new")
	public String getShopNew() {
		return "views/categorypage/new.html";
	}
	
	@GetMapping("/public/shop/ranking")
	public String getShopRanking() {
		return "views/categorypage/ranking.html";
	}




}
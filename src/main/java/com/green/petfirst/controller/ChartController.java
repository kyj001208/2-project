package com.green.petfirst.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChartController {
	
	private final AdminService service;
	
	@GetMapping("/api/salesData")
	public Map<String, Long> SalesDataProcess(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
	    return service.SalesDataProcess(startDate, endDate);
	}
	
}

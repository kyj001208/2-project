package com.green.petfirst.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.petfirst.service.chart.ChartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChartController {
	
	private final ChartService service;
	
	@GetMapping("/api/salesData")
	public ResponseEntity<Map<String, Long>> SalesDataProcess(
			@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            Map<String, Long> salesData = service.SalesDataProcess(startDate, endDate);
            return ResponseEntity.ok(salesData);
        } catch (Exception e) {
            // 에러 로그 출력 및 내부 서버 오류 응답 반환
            System.err.println("Error fetching sales data: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	@GetMapping("/api/downloadSalesReport")
    public ResponseEntity<byte[]> downloadSalesReport(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        byte[] excelData = service.generateExcelReport(startDate, endDate);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "sales_report.xlsx");
		headers.setContentLength(excelData.length);

		return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
    }
	
}

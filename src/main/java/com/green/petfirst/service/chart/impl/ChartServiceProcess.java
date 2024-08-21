package com.green.petfirst.service.chart.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.green.petfirst.domain.entity.OrderEntity;
import com.green.petfirst.domain.repository.OrderRepository;
import com.green.petfirst.service.chart.ChartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChartServiceProcess implements ChartService {

	private final OrderRepository orderRep;

	@Override
	public Map<String, Long> SalesDataProcess(LocalDate startDate, LocalDate endDate) {
	    List<OrderEntity> orders = orderRep.findByOrderDateBetween(startDate, endDate);
	    Map<String, Long> salesData = new HashMap<>();

	    for (OrderEntity order : orders) {
	        String orderDate = order.getOrderDate().toString(); // 날짜를 문자열로 변환
	        long total = order.getTotal();
	        salesData.merge(orderDate, total, Long::sum);
	    }

	    return salesData;
	}

	@Override
	public byte[] generateExcelReport(LocalDate startDate, LocalDate endDate) {
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Sales Report");

	    // 헤더 생성
	    Row headerRow = sheet.createRow(0);
	    Cell dateHeader = headerRow.createCell(0);
	    dateHeader.setCellValue("Date");
	    Cell salesHeader = headerRow.createCell(1);
	    salesHeader.setCellValue("Sales");

	    // 데이터 가져오기
	    Map<String, Long> salesData = SalesDataProcess(startDate, endDate);

	    // 데이터를 날짜순으로 정렬
	    List<Map.Entry<String, Long>> sortedSalesData = salesData.entrySet().stream()
	            .sorted(Map.Entry.comparingByKey())
	            .toList();

	    // 데이터 추가
	    int rowNum = 1;
	    for (Map.Entry<String, Long> entry : sortedSalesData) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(entry.getKey());
	        row.createCell(1).setCellValue(entry.getValue());
	    }

	    // 파일로 변환
	    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
	        workbook.write(bos);
	        return bos.toByteArray();
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to generate Excel report", e);
	    }
	}


}

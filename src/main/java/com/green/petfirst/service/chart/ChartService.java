package com.green.petfirst.service.chart;

import java.time.LocalDate;
import java.util.Map;

public interface ChartService {
	
	Map<String, Long> SalesDataProcess(LocalDate startDate, LocalDate endDate);

	byte[] generateExcelReport(LocalDate startDate, LocalDate endDate);
	
}

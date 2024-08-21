package com.green.petfirst.service.admin;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.deliver.DeliverDTO;

public interface AdminService {

	void ListProcess(Model model);

	Page<DeliverDTO> getDeliverList(String devNo, String devTime, String devComplete, String devCompany,
			Pageable pageable);

	Map<String, Long> SalesDataProcess(LocalDate startDate, LocalDate endDate);

}

package com.green.petfirst.service.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.deliver.DeliverDTO;

public interface AdminService {

	void ListProcess(Model model);

	Page<DeliverDTO> getDeliverList(String devNo, String devTime, String devComplete, String devCompany,
			Pageable pageable);

	void salesProcess(Model model);

}

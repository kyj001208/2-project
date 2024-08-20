package com.green.petfirst.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.petfirst.domain.dto.deliver.DeliverDTO;
import com.green.petfirst.domain.repository.DeliverRepository;
import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService service;
	private final DeliverRepository deliverRep;
	
	@GetMapping("/admin/petfir")
	public String List(Model model) {
		service.ListProcess(model);
		return "views/admin/petfir";
	}
	
	@GetMapping("/admin/SaleStatus")
    public String Sales(Model model) {
        service.salesProcess(model);
        return "views/admin/saleStatus";
    }
	
	@GetMapping("/admin/deliver")
	public String deliver(
	        @RequestParam(value = "devNo", required = false) String devNo,
	        @RequestParam(value = "devTime", required = false) String devTime,
	        @RequestParam(value = "devComplete", required = false) String devComplete,
	        @RequestParam(value = "devCompany", required = false) String devCompany,
	        @RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "12") int size,
	        Model model) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<DeliverDTO> deliverPage = service.getDeliverList(devNo, devTime, devComplete, devCompany, pageable);

	    int totalPages = deliverPage.getTotalPages();
	    int currentPage = deliverPage.getNumber();
	    int pageBlockSize = 5; // 한 번에 표시할 페이지 번호 수

	    int startPage = (currentPage / pageBlockSize) * pageBlockSize;
	    int endPage = Math.min(startPage + pageBlockSize, totalPages);

	    model.addAttribute("delivers", deliverPage.getContent());
	    model.addAttribute("companies", deliverRep.findDistinctCompanies());
	    model.addAttribute("selectedCompany", devCompany);
	    model.addAttribute("devNo", devNo);
	    model.addAttribute("devTime", devTime);
	    model.addAttribute("devComplete", devComplete);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);

	    return "views/admin/deliver"; 
	}


	
}

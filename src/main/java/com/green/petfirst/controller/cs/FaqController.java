package com.green.petfirst.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.green.petfirst.domain.dto.cs.faq.FaqSaveDTO;
import com.green.petfirst.domain.dto.cs.faq.FaqUpdateDTO;
import com.green.petfirst.service.faq.FaqService;

import lombok.RequiredArgsConstructor;

/**
 * FAQ 관리 기능을 담당하는 컨트롤러 클래스입니다.
 * 이 클래스는 FAQ 목록 조회, 등록, 수정, 삭제 및 상세 조회를 처리합니다.
 */
@Controller
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService; // FAQ 관련 서비스 객체를 주입받습니다.
    /**
     * 일반 사용자를 위한 FAQ 목록 페이지를 반환합니다.
     */
    
   
    @GetMapping("/public/shop/faq")
    public String publicList() {
        return "views/customer/faq/list"; // 일반 사용자를 위한 FAQ 목록 페이지
    }
    
    @GetMapping("/public/shop/faq/{division}")
    public String publicListData(Model model,@PathVariable(name = "division") int division) {
    	faqService.faqListPrecess(model, division);
        return "views/customer/faq/list-data";  // 일반 사용자를 위한 FAQ 목록 페이지
    }
    
    @GetMapping("/admin/customer/faq")
    public String adminFaqPage() {
        return "views/customer/faq/admin-list"; // 일반 사용자를 위한 FAQ 목록 페이지
    }
    
    @GetMapping("/admin/customer/faq/new")
    public String newFaq() {
    	return "views/customer/faq/write"; // 새로운 FAQ 등록 페이지
    }

    
    // 관리자용 FAQ 목록 페이지
    @GetMapping("/admin/customer/faq/{division}")
    public String adminList(Model model,@PathVariable(name = "division") int division) {
        faqService.faqListPrecess(model, division);
        return "views/customer/faq/list-data"; 
    }

    /**
     * 관리자가 FAQ를 등록합니다. 등록 후 FAQ 목록 페이지로 리다이렉트합니다.
     */
    @PostMapping("/admin/customer/faq")
    public String adminSave(FaqSaveDTO dto) {
        faqService.saveFaq(dto); // FAQ를 저장합니다.
        return "redirect:/admin/customer/faq"; // FAQ 목록 페이지로 리다이렉트
    }
    
    
    /**
     * 관리자가 FAQ를 삭제합니다. 삭제 후 FAQ 목록 페이지로 리다이렉트합니다.
     */
    @DeleteMapping("/admin/customer/faq/delete/{faqNo}")
    public String delete(@PathVariable("faqNo") long faqNo) {
        faqService.deleteFaq(faqNo); // FAQ를 삭제합니다.
        return "redirect:/admin/customer/faq"; // FAQ 목록 페이지로 리다이렉트
    }
    
    /**
     * 관리자가 FAQ를 수정합니다. 수정 후 FAQ 목록 페이지로 리다이렉트합니다.
     */
    @PutMapping("/admin/customer/faq/{faqNo}")
    public String update(@PathVariable("faqNo") long faqNo, FaqUpdateDTO dto) {
    	faqService.updateProcess(faqNo, dto); // FAQ를 저장합니다.
    	return "redirect:/admin/customer/faq/detail/{faqno}"; // FAQ 목록 페이지로 리다이렉트
    }

}

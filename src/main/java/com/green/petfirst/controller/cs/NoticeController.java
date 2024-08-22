package com.green.petfirst.controller.cs;

import com.green.petfirst.domain.dto.cs.notice.NoticeListDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeSaveDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeUpdateDTO;
import com.green.petfirst.service.notice.NoticeService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/customer/notices")
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 일반 유저가 공지사항을 볼 수 있는 페이지로 이동
     */
    @GetMapping("/public")
    public String publicList() {
        return "/views/customer/notice/list"; // 공지사항 리스트 페이지로 이동
    }

    /**
     * 관리자가 새로운 공지사항을 작성하는 페이지로 이동
     */
    @GetMapping("/new")
    public String newNotice() {
    	return "views/customer/notice/write"; // 새로운 공지사항 작성 페이지로 이동
    }
    
    /**
     * 관리자가 공지사항 목록을 조회
     */
	@GetMapping
	public String adminList(Model model) {
		noticeService.adminListNotice(model); // 공지사항 목록을 모델에 추가
		return "views/customer/notice/admin-list"; // 관리자 공지사항 리스트 페이지로 이동
	}
    
    /**
     * 관리자가 공지사항을 등록 후 목록 페이지로 리디렉션
     */
    @PostMapping("/noticeSave")
    public String adminSave(NoticeSaveDTO dto) {
    	noticeService.saveNotice(dto); // 공지사항 저장
        return "redirect:/admin/customer/notices"; // 공지사항 리스트 페이지로 리디렉션
    }

    /**
     * 관리자가 공지사항 상세 페이지로 이동
     */
    @GetMapping("/detail/{no}")
    public String adminDetail(@PathVariable("no") long no, Model model) {	
    	noticeService.detailProcess(no, model); // 공지사항 상세 정보 조회 후 모델에 추가
        return "views/customer/notice/admin-detail"; // 공지사항 상세 페이지로 이동
    }
    
    /**
     * 관리자가 공지사항을 삭제 후 목록 페이지로 리디렉션
     */
    @DeleteMapping("/delete/{no}")
    public String delete(@PathVariable("no") long no) {
    	   noticeService.deleteNotice(no); // 공지사항 삭제
           return "redirect:/admin/customer/notices"; // 공지사항 리스트 페이지로 리디렉션
    }
    
    /**
     * 관리자가 공지사항을 수정 후 상세 페이지로 리디렉션
     */
    @PutMapping("/{no}")
    public String update(@PathVariable("no")long no, NoticeUpdateDTO dto) {	
		noticeService.updateProcess(no, dto); // 공지사항 수정
        return "redirect:/admin/customer/notices/detail/{no}"; // 수정된 공지사항의 상세 페이지로 리디렉션
    }
}

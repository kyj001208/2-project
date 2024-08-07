package com.green.petfirst.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class NoticeController {
	
	/**
	 * 일반유저
	 * 
	 * 공지사항을 볼 수 있는 페이지
	 */
	@GetMapping("/public/customer/notices")
	public String publicList() {
		return "views/customer/notice/list";
	}
	
	/**
	 * 관리자용
	 * 
	 * 공지사항을 볼 수 있는 페이지
	 */
	@GetMapping("/admin/customer/notices")
	public String adminList() {
		return "views/customer/notice/admin-list";
	}
	
	/**
	 * 관리자가 공지사항을 등록 후 목록으로 이동
	 */
	@PostMapping("/admin/customer/notices")
	public String save() {
		return "redirect:/admin/customer/notices";
	}
	
	/**
	 * 관리자가 공지사항을 등록 할 수 있는 페이지
	 */
	@GetMapping("/admin/customer/notices/new")
	public String newrs() {
		return "views/customer/notice/wirte";
	}
	
	/**
	 * 관리자가 공지사항을 수정 후 목록으로 이동
	 */
	@PutMapping("/admin/customer/notices/{noticeNo}")
	public String change(@PathVariable("noticeNo") long noticeNo) {
		return "redirect:/admin/customer/notices";
	}
	
	/**
	 * 관리자가 공지사항을 등록 후 목록으로 이동
	 */
	@DeleteMapping("/admin/customer/notices/{noticeNo}")
	public String delete(@PathVariable("noticeNo") long noticeNo) {
		return "redirect:/admin/customer/notices";
	}
}

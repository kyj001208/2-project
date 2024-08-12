package com.green.petfirst.controller.cs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.petfirst.domain.dto.cs.notice.NoticeDetailDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeListDTO;

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
	public String adminList(Model model) {
		// 임시데이터
		List<NoticeListDTO> noticeList = new ArrayList<>();

		for (long i = 1; i <= 10; i++) {
			NoticeListDTO notice = NoticeListDTO.builder().noticeNo(i).title("Notice Title " + i)
					.createAt(LocalDateTime.now().minusDays(i)).build();
			noticeList.add(notice);
		}

		model.addAttribute("list", noticeList);
		return "views/customer/notice/admin-list";
	}

	/**
	 * 관리자가 공지사항을 등록처리 후 목록으로 이동
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
	 * 관리자가 공지사항을 삭제 후 목록으로 이동
	 */
	@DeleteMapping("/admin/customer/notices/{noticeNo}")
	public String delete(@PathVariable("noticeNo") long noticeNo) {
		return "redirect:/admin/customer/notices";
	}

	/**
	 * 관리자가 공지사항 상세페이지으로 이동
	 */
	@GetMapping("/admin/customer/notices/{noticeNo}")
	public String detail(@PathVariable("noticeNo") long noticeNo, Model model) {
		//데모 상세데이터
		String content=""
				+ "<p>공지사항 내용이 여기에 표시됩니다.</p>"
				+ "<p>강아지와 고양이 펫 관련된 상품판매 사이트입니다.</p>"
				+ "<p>사이트 이름은 PETFIR 입니다.</p>"
				+ "<p>감사합니다.</p>";
		NoticeDetailDTO dto=NoticeDetailDTO.builder()
					.noticeNo(noticeNo)
					.title("공지사항 제목 입니다.")
					.content(content)
					.createAt(LocalDateTime.now().minusDays(noticeNo))
					.build();
		
		model.addAttribute("detail", dto);
		
		return "views/customer/notice/admin-detail";
	}
}
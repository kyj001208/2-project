package com.green.petfirst.service.notice.impl;

import com.green.petfirst.domain.dto.cs.notice.NoticeDetailDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeListDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeSaveDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeUpdateDTO;
import com.green.petfirst.domain.entity.cs.NoticeEntity;
import com.green.petfirst.domain.repository.NoticeRepository;
import com.green.petfirst.service.notice.NoticeService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeServiceProcess implements NoticeService {

	private final NoticeRepository noticeRepository;
	
	//공지사항을 저장 
	@Override
	public void saveNotice(NoticeSaveDTO dto) {
        // NoticeEntity 객체를 데이터베이스에 저장합니다.
        noticeRepository.save(dto.toEntity());
	}
	//공지사항 리스트
	@Override
	public void adminListNotice(Model model) {
	    // 모든 공지사항을 조회하고, DTO로 변환하여 리스트에 추가합니다.
	    List<NoticeListDTO> noticeList = noticeRepository.findAll().stream()
	        .map(NoticeEntity::toNoticeListDTO)
	        .collect(Collectors.toList());
	    // 모델에 공지사항 리스트를 추가합니다.
	    model.addAttribute("list", noticeList);
	}

	//공지사항 상세페이지
	@Override
	public void detailProcess(long no, Model model) {
		// 공지사항을 고유 번호로 조회합니다. 조회되지 않으면 예외를 발생시킵니다.
		NoticeEntity notice = noticeRepository.findById(no)
			.orElseThrow(() -> new RuntimeException("Notice not found with id: " + no));
		// 모델에 공지사항의 상세 정보를 추가합니다.
		model.addAttribute("detail", notice.toDetailDTO());
	}

	//공지사항 삭제 
	@Override
	public void deleteNotice(long no) {
		// 공지사항을 고유 번호로 조회하여 삭제합니다. 조회되지 않으면 예외를 발생시킵니다.
		noticeRepository.delete(noticeRepository.findById(no).orElseThrow());
	}

	//공지사항 수정
	@Override
	@Transactional
	public void updateProcess(long no, NoticeUpdateDTO dto) {
		// 1. 수정할 공지사항을 조회하고
		// 2. 조회된 공지사항에 변경사항을 적용합니다.
		noticeRepository.findById(no).orElseThrow().update(dto);	
	}
	
}

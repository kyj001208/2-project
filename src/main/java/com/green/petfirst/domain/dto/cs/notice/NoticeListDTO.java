package com.green.petfirst.domain.dto.cs.notice;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 자동으로 생성
@NoArgsConstructor // 인자가 없는 기본 생성자를 자동으로 생성
public class NoticeListDTO {
	
	private Long no; // 공지사항 번호
	private String title; // 공지사항 제목
	private LocalDateTime createdAt; // 공지사항 생성 일자
	
	private LocalDateTime updatedAt; // 공지사항 생성 일자
	
}

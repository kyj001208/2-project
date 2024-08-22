package com.green.petfirst.domain.dto.cs.qna;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnaDetailDTO {
	
	private Long qnaNo; // Q&A 번호
	private String title; // Q&A 제목
	private String content; // Q&A 내용
	private LocalDateTime createdAt; // Q&A 생성일자
	private LocalDateTime updatedAt; // Q&A 생성일자
}

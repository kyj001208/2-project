package com.green.petfirst.domain.dto.cs.qna;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnaListDTO {
	
    private Long qnaNo; // Q&A 번호
    private String title; // Q&A 제목
    private LocalDateTime createdAt; // Q&A 생성 일자
    private LocalDateTime updatedAt; // Q&A 생성 일자
}

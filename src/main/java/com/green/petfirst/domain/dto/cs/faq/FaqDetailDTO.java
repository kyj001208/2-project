package com.green.petfirst.domain.dto.cs.faq;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FaqDetailDTO {
    
    private Long faqNo;         // FAQ의 고유 식별 번호
    private String title;      // FAQ 제목
    private String content;    // FAQ 내용
    private String division;
    private LocalDateTime createdAt; // FAQ 생성 날짜 및 시간
    private LocalDateTime updatedAt; // FAQ 생성 날짜 및 시간
    
}

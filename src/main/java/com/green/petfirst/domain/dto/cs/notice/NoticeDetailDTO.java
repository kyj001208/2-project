package com.green.petfirst.domain.dto.cs.notice;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class NoticeDetailDTO {
	
	private Long no;          // 공지사항 번호
    private String title;           // 공지사항 제목
    private String content;         // 공지사항 내용
    private LocalDateTime createdAt; // 공지사항 생성 일자
    private LocalDateTime updatedAt; // 공지사항 생성 일자
	
}

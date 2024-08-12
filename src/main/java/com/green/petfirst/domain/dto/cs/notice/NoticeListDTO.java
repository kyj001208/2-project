package com.green.petfirst.domain.dto.cs.notice;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeListDTO {
	private Long noticeNo;
	private String title;
	private LocalDateTime createAt;
	
}

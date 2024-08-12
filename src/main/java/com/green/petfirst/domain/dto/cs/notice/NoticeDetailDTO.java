package com.green.petfirst.domain.dto.cs.notice;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeDetailDTO {
	private Long noticeNo;
	private String title;
	private String content;
	private LocalDateTime createAt;
	
}

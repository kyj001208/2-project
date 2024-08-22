package com.green.petfirst.domain.dto.cs.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter//Controller 에서 부트가 파라미터데이터()를 매핑
public class NoticeUpdateDTO {
	
	private Long no;          		// 공지사항 번호
    private String title;           // 공지사항 제목
    private String content;         // 공지사항 내용
	
}

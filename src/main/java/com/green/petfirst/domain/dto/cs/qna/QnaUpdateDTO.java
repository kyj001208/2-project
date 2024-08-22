package com.green.petfirst.domain.dto.cs.qna;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter//Controller 에서 부트가 파라미터데이터()를 매핑
public class QnaUpdateDTO {
	
	private Long qnaNo;          	// Q&A 번호
    private String title;           // Q&A 제목
    private String content;         // Q&A 내용
}
